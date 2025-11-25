package white.ball.success_diary.presentation.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import white.ball.domain.collection.MusicCollection
import white.ball.domain.collection.TagCollection
import white.ball.domain.extension_model.ItemStatus
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.FocusTime
import white.ball.domain.model.Music
import white.ball.domain.model.Tag
import white.ball.domain.use_case.model.CoffeeCoinUseCases
import white.ball.domain.use_case.model.FocusTimeUseCases
import white.ball.domain.use_case.model.MusicUseCases
import white.ball.domain.use_case.model.TagUseCases
import white.ball.success_diary.platform.app.service.TimerWorker
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val tagUseCases: TagUseCases,
    private val coffeeCoinUseCases: CoffeeCoinUseCases,
    private val musicUseCases: MusicUseCases,
    private val focusTimeUseCases: FocusTimeUseCases,
) : ViewModel() {

    private val _coffeeCoins = MutableStateFlow<CoffeeCoin?>(null)
    val coffeeCoin: Flow<CoffeeCoin?> = _coffeeCoins

    // Music
    private var mediaPlayerShortMusic: MediaPlayer? = null

    private var playMusicJob: Job? = null

    private val _musicList = MutableStateFlow<List<Music>>(emptyList())
    val musicList: Flow<List<Music>> = _musicList

    private val _availableMusic = _musicList
        .map { list ->
            list.filter { it.status == ItemStatus.AVAILABLE }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val availableMusic: Flow<List<Music>> = _availableMusic

    private val _selectedPlayMusic = MutableStateFlow<Int?>(null)
    val selectedPlayMusic: Flow<Int?> = _selectedPlayMusic

    private val _selectedShortPlayMusic = MutableStateFlow<Int?>(null)
    val selectedShortPlayMusic: Flow<Int?> = _selectedShortPlayMusic

    // Tag
    private val _tagList = MutableStateFlow<List<Tag>>(emptyList())
    val tagList: Flow<List<Tag>> = _tagList

    private val _availableTag = MutableStateFlow<List<Tag>>(emptyList())
    val availableTag: Flow<List<Tag>> = _availableTag

    // navBar
    private val _selectedNavigationBottomBarIndex = MutableStateFlow(0)

    val selectedNavigationBottomBarIndex: Flow<Int> = _selectedNavigationBottomBarIndex

    // Timer

    private var timerWorkId: UUID? = null
    private var workManager: WorkManager

    private val _timeLeft = MutableStateFlow(-1)
    val timeLeft: StateFlow<Int> = _timeLeft

    private val _isStartTimer = MutableStateFlow(false)
    val isStartTimer: Flow<Boolean> = _isStartTimer

    private val _selectedTag = MutableStateFlow<Tag?>(null)
    val selectedTag: Flow<Tag?> = _selectedTag

    private val _selectedTime = MutableStateFlow(20)
    val selectedTime: Flow<Int> = _selectedTime

    private val _timerFinish = MutableStateFlow(false)
    val timerFinish: Flow<Boolean> = _timerFinish

    // dialogs
    private val _isOpenDialogBalance = MutableStateFlow(false)
    val isOpenDialogBalance: Flow<Boolean> = _isOpenDialogBalance

    private val _isOpenDialogCustomizeTimerCollection = MutableStateFlow(false)
    val isOpenDialogCustomizeTimerCollection: Flow<Boolean> = _isOpenDialogCustomizeTimerCollection

    // focusTime up 3

    private val _focusTimeCoffee = MutableStateFlow<FocusTime?>(null)
    val focusTimeCoffee: Flow<FocusTime?> = _focusTimeCoffee

    private val tagCollection = TagCollection()
    private val musicCollection = MusicCollection()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            coffeeCoinUseCases.getCoffeeCoinsUseCase().collect { coffeeCoin ->
                if (coffeeCoin == null) {
                    coffeeCoinUseCases.createCoffeeCoinUseCase(CoffeeCoin())
                } else {
                    _coffeeCoins.value = coffeeCoin
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            musicUseCases.getMusicListUseCase().collect { list ->
                if (list.isEmpty()) {
                    musicUseCases.insertMusicListUseCase(musicCollection.musicList)
                } else {
                    _musicList.value = list
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            tagUseCases.getTagListUseCase().collect { list ->
                if (list.isEmpty()) {
                    tagUseCases.insertTagListUseCase(tagCollection.tagList)
                } else {
                    _tagList.value = list
                    _availableTag.value = list
                        .filter { it.status == ItemStatus.AVAILABLE }
                    _selectedTag.value = tagCollection.tagList[8]
                }
            }
        }

        viewModelScope.launch {
            _availableMusic.collect { list ->
                if (list.isNotEmpty() && _selectedPlayMusic.value == null) {
                    _selectedPlayMusic.value = list[0].rawResId
                }
            }
        }

        viewModelScope.launch {
            focusTimeUseCases.getFocusTimeUseCase().collect {
                _focusTimeCoffee.value = it
            }
        }

        workManager = WorkManager.getInstance(context)
    }

    fun setTimer(turn: Boolean) {
        _isStartTimer.value = turn
    }

    fun setSelectedNavigationBottomBarIndex(index: Int) {
        _selectedNavigationBottomBarIndex.value = index
    }

    fun selectedMusic(musicRawResId: Int) {
        _selectedPlayMusic.value = musicRawResId
    }

    fun selectedAndPlayShortMusic(musicResId: Int) {
        if (musicResId == _selectedShortPlayMusic.value) {
            stopLongMusic()
            return
        }

        stopLongMusic()

        _selectedShortPlayMusic.value = musicResId

        playMusicJob = viewModelScope.launch {
            mediaPlayerShortMusic = MediaPlayer.create(context, musicResId)
            mediaPlayerShortMusic?.start()

            delay(10_000)

            stopShortMusic()
        }
    }

    fun stopShortMusic() {
        playMusicJob?.cancel()
        playMusicJob = null

        mediaPlayerShortMusic?.stop()
        mediaPlayerShortMusic?.release()
        mediaPlayerShortMusic = null

        _selectedShortPlayMusic.value = 0
    }

    fun playLongMusic() {
        playMusicJob = viewModelScope.launch {
            _selectedPlayMusic.value?.let {
                mediaPlayerShortMusic = MediaPlayer.create(context, it)
            }

            mediaPlayerShortMusic?.let {
                it.isLooping = true
                it.start()
            }
        }
    }

    fun stopLongMusic() {
        playMusicJob?.cancel()
        playMusicJob = null

        mediaPlayerShortMusic?.stop()
        mediaPlayerShortMusic?.release()
        mediaPlayerShortMusic = null
    }

    fun isPlayingMusic() = mediaPlayerShortMusic?.isPlaying ?: false

    fun setDialogBalance() {
        _isOpenDialogBalance.value = !_isOpenDialogBalance.value
    }

    fun setDialogCustomizeTimer(turn: Boolean) {
        _isOpenDialogCustomizeTimerCollection.value = turn
    }

    fun setSelectedTag(tag: Tag) {
        _selectedTag.value = tag
    }

    fun setSelectedTime(time: Int) {
        _selectedTime.value = time
    }

    suspend fun updateBalance(balance: Int) {
        coffeeCoinUseCases.updateBalanceUseCase(balance)
    }

    suspend fun buyTag(tag: Tag): Boolean {
        val coin = _coffeeCoins.value ?: return false
        if (tag.price > coin.balance) return false

        tagUseCases.updateTagUseCase(
            tag.copy(
                status = ItemStatus.AVAILABLE,
                price = 0,
            )
        )

        updateBalance(coin.balance - tag.price)
        return true
    }

    suspend fun buyMusic(music: Music): Boolean {
        val coin = _coffeeCoins.value ?: return false
        if (music.price > coin.balance) return false

        musicUseCases.updateMusicUseCase(
            music.copy(
                status = ItemStatus.AVAILABLE,
                price = 0,
            )
        )

        updateBalance(coin.balance - music.price)
        return true
    }

    suspend fun buyFocusTimeCoffee(time: Int) = when (time) {
        24 -> {
            focusTimeUseCases.insertFocusTimeUseCase(
                FocusTime(
                    focusTimeId = 0,
                    focusTime = LocalDateTime.now().plusHours(24).second
                )
            )
        }

        3 -> {
            focusTimeUseCases.insertFocusTimeUseCase(
                FocusTime(
                    focusTimeId = 0,
                    focusTime = LocalDateTime.now().plusDays(3).second
                )
            )
        }

        else -> throw IllegalArgumentException("illegal time for focus time coffee")
    }

    fun startTimer() {

        if (_isStartTimer.value) return
        _isStartTimer.value = true

        val workRequest = OneTimeWorkRequestBuilder<TimerWorker>()
            .setInputData(workDataOf(TimerWorker.TIME_KEY to _selectedTime.value))
            .build()

        timerWorkId = workRequest.id

        workManager.enqueue(workRequest)

        observeTimer()
    }

    private fun observeTimer() {
        timerWorkId?.let { id ->
            viewModelScope.launch {
                delay(1_000)
                workManager.getWorkInfoByIdLiveData(id)
                    .asFlow()
                    .collectLatest {
                        if (it != null) {

                            _timeLeft.value = it.progress.getInt(TimerWorker.TIME_PROGRESS, 1)

                            if (it.state == androidx.work.WorkInfo.State.SUCCEEDED) {
                                _isStartTimer.value = false
                            }
                        }
                    }
            }
        }
    }

    suspend fun stopTimer() {
        setTimer(false)
        workManager.cancelAllWork()
        delay(500)
        _timeLeft.value = _selectedTime.value * 60
    }

    suspend fun takePrize() {

        var updatedBalance = (_coffeeCoins.value?.balance ?: 0) + _selectedTime.value
        val timeNow = LocalDateTime.now().second

        if ((_focusTimeCoffee.value?.focusTime ?: 0) < timeNow) {
            updatedBalance += _selectedTime.value * 3
            _coffeeCoins.value = _coffeeCoins.value?.copy(
                balance = updatedBalance
            )
        } else {
            _focusTimeCoffee.value?.let {
                focusTimeUseCases.deleteFocusTimeUseCase(it)
            }
        }

        _coffeeCoins.value = _coffeeCoins.value?.copy(
            balance = updatedBalance
        )

        coffeeCoinUseCases.updateBalanceUseCase(updatedBalance)
    }
}