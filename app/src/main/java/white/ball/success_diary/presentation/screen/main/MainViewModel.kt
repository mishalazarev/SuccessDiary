package white.ball.success_diary.presentation.screen.main

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import white.ball.domain.collection.MusicCollection
import white.ball.domain.collection.TagCollection
import white.ball.domain.extension_model.ItemStatus
import white.ball.domain.model.CoffeeCoinDomainModel
import white.ball.domain.model.MusicDomainModel
import white.ball.domain.model.TagDomainModel
import white.ball.domain.use_case.model.CoffeeCoinUseCases
import white.ball.domain.use_case.model.MusicUseCases
import white.ball.domain.use_case.model.TagUseCases
import white.ball.success_diary.platform.app.service.TimerWorker
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val tagUseCases: TagUseCases,
    private val coffeeCoinUseCases: CoffeeCoinUseCases,
    private val musicUseCases: MusicUseCases,
) : ViewModel() {

    private val _coffeeCoins = MutableStateFlow<CoffeeCoinDomainModel?>(null)
    val coffeeCoin: Flow<CoffeeCoinDomainModel?> = _coffeeCoins

    // Music
    private var mediaPlayerShortMusic: MediaPlayer? = null

    private var playMusicJob: Job? = null

    private val _musicList = MutableStateFlow<List<MusicDomainModel>>(emptyList())
    val musicList: Flow<List<MusicDomainModel>> = _musicList

    private var _availableMusic = _musicList
        .map { list ->
            list.filter { it.status == ItemStatus.AVAILABLE }
        }
    val availableMusic: Flow<List<MusicDomainModel>> = _availableMusic

    private val _selectedPlayMusic = MutableStateFlow<Int?>(null)
    val selectedPlayMusic: Flow<Int?> = _selectedPlayMusic

    private val _selectedShortPlayMusic = MutableStateFlow<Int?>(null)
    val selectedShortPlayMusic: Flow<Int?> = _selectedShortPlayMusic

    // Tag
    private val _tagList = MutableStateFlow<List<TagDomainModel>>(emptyList())
    val tagList: Flow<List<TagDomainModel>> = _tagList

    private var _availableTag = _tagList
        .map { list ->
            list.filter { it.status == ItemStatus.AVAILABLE }
        }
    val availableTag: Flow<List<TagDomainModel>> = _availableTag

    // navBar
    private val _selectedNavigationBottomBarIndex = MutableStateFlow(0)

    val selectedNavigationBottomBarIndex: Flow<Int> = _selectedNavigationBottomBarIndex

    // Timer

    private var activeWorkId: UUID? = null
    private var pausedSeconds: Int? = null

    private var workManager: WorkManager

    private val _timerLeft = MutableStateFlow(20 * 60)
    val timerLeft: StateFlow<Int> = _timerLeft

    private val _isStartTimer = MutableStateFlow(false)
    val isStartTimer: Flow<Boolean> = _isStartTimer

    private val _selectedTag = MutableStateFlow<Int?>(null)
    val selectedTag: Flow<Int?> = _selectedTag

    private val _selectedTime = MutableStateFlow(20)
    val selectedTime: Flow<Int> = _selectedTime

    private val _timerFinish = MutableStateFlow(false)
    val timerFinish: Flow<Boolean> = _timerFinish

    private var timerObserverJob: Job? = null

    // dialogs
    private val _isOpenDialogCustomizeTimerCollection = MutableStateFlow(false)
    val isOpenDialogCustomizeTimerCollection: Flow<Boolean> = _isOpenDialogCustomizeTimerCollection

    private val _isOpenDialogGiveUp = MutableStateFlow(false)
    val isOpenDialogGiveUp: Flow<Boolean> = _isOpenDialogGiveUp

    init {
        viewModelScope.launch(Dispatchers.IO) {
            coffeeCoinUseCases.getCoffeeCoinsUseCase().collect { coffeeCoin ->
                if (coffeeCoin == null) {
                    coffeeCoinUseCases.createCoffeeCoinUseCase(CoffeeCoinDomainModel())
                } else {
                    _coffeeCoins.value = coffeeCoin
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            musicUseCases.getMusicListUseCase().collect { list ->
                if (list.isEmpty()) {
                    val musicList = mutableListOf<MusicDomainModel>()

                    MusicCollection.entries
                        .forEach { musicCollection ->
                            musicList.add(
                                MusicDomainModel(
                                    musicId = musicCollection.musicId,
                                    title = musicCollection.title,
                                    artist = musicCollection.artist,
                                    imageResId = musicCollection.imageResId,
                                    rawResId = musicCollection.rawResId,
                                    status = musicCollection.status,
                                    price = musicCollection.price
                                )
                            )
                        }

                    musicUseCases.insertMusicListUseCase(musicList)
                } else {
                    _musicList.value = list
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            tagUseCases.getTagListUseCase().collect { list ->
                if (list.isEmpty()) {
                    val tagList = mutableListOf<TagDomainModel>()

                    TagCollection.entries
                        .forEach { tagCollection ->
                            tagList.add(
                                TagDomainModel(
                                    tagId = tagCollection.tagId,
                                    title = tagCollection.title,
                                    imageResId = tagCollection.imageResId,
                                    status = tagCollection.status,
                                    price = tagCollection.price,
                                )
                            )
                        }

                    tagUseCases.insertTagListUseCase(tagList)
                } else {
                    _tagList.value = list
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
            _availableTag.collect { list ->
                if (list.isNotEmpty() && _selectedTag.value == null) {
                    _selectedTag.value = list[0].imageResId
                }
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


    fun setTimerFinish(turn: Boolean) {
        _timerFinish.value = turn
    }

    fun setDialogCustomizeTimer(turn: Boolean) {
        _isOpenDialogCustomizeTimerCollection.value = turn
    }

    fun setSelectedTag(tag: TagDomainModel) {
        _selectedTag.value = tag.imageResId
    }

    fun setSelectedTime(time: Int) {
        _selectedTime.value = time
        _timerLeft.value = time * 60
    }

    fun setDialogGiveUp(turn: Boolean) {
        _isOpenDialogGiveUp.value = turn
    }


    suspend fun updateBalance(balance: Int) {
        coffeeCoinUseCases.updateBalanceUseCase(balance)
    }

    suspend fun buyTag(tag: TagDomainModel): Boolean {
        val coin = _coffeeCoins.value ?: return false
        if (tag.price > coin.balance) return false


        updateBalance(coin.balance - tag.price)

        val boughtTag = tag.copy(
            price = 0,
            status = ItemStatus.AVAILABLE
        )

        tagUseCases.updateTagUseCase(boughtTag)

        return true
    }

    suspend fun buyMusic(music: MusicDomainModel): Boolean {
        val coin = _coffeeCoins.value ?: return false
        if (music.price > coin.balance) return false

        updateBalance(coin.balance - music.price)

        val boughtMusic = music.copy(
            price = 0,
            status = ItemStatus.AVAILABLE
        )

        musicUseCases.updateMusicUseCase(boughtMusic)

        return true
    }

    fun startTimer() {
        setTimer(true)

        val workRequest = OneTimeWorkRequestBuilder<TimerWorker>()
            .setInputData(
                workDataOf(
                    TimerWorker.Companion.TIMER_KEY to _timerLeft.value
                )
            )
            .build()

        activeWorkId = workRequest.id
        pausedSeconds = null

        workManager.enqueue(workRequest)

        observeTimer()
    }

    private fun observeTimer() {
        timerObserverJob?.cancel()

        activeWorkId?.let { id ->
            timerObserverJob = viewModelScope.launch {
                delay(1_000)
                workManager.getWorkInfoByIdFlow(id)
                    .collectLatest { info ->

                        info ?: return@collectLatest

                        _timerLeft.value = info.progress.getInt(
                            TimerWorker.Companion.TIMER_PROGRESS,
                            _selectedTime.value * 60
                        )

                        if (info.state == WorkInfo.State.SUCCEEDED) {
                            stopLongMusic()
                            _isStartTimer.value = false
                            _timerFinish.value = true
                            _timerLeft.value = _selectedTime.value * 60
                        }
                    }
            }
        }
    }

    fun pauseTimer() {
        setTimer(false)

        val id = activeWorkId ?: return

        pausedSeconds = _timerLeft.value

        workManager.cancelWorkById(id)

        activeWorkId = null
    }

    fun stopTimer() {
        setTimer(false)

        activeWorkId?.let { workManager.cancelWorkById(it) }
        activeWorkId = null
        pausedSeconds = null

        _timerLeft.value = _selectedTime.value * 60
    }

    suspend fun takePrize() {
        val updatedBalance = (_coffeeCoins.value?.balance ?: 0) + _selectedTime.value

        withContext(Dispatchers.IO) {
            coffeeCoinUseCases.updateBalanceUseCase(updatedBalance)
        }
        _coffeeCoins.value = _coffeeCoins.value?.copy(balance = updatedBalance)
    }
}