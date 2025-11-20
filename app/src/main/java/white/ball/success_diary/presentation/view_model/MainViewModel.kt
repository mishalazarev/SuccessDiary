package white.ball.success_diary.presentation.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import white.ball.domain.collection.MusicCollection
import white.ball.domain.collection.TagCollection
import white.ball.domain.extension_model.ItemStatus
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.Music
import white.ball.domain.model.Tag
import white.ball.domain.use_case.model.CoffeeCoinUseCases
import white.ball.domain.use_case.model.MusicUseCases
import white.ball.domain.use_case.model.TagUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val tagUseCases: TagUseCases,
    private val coffeeCoinUseCases: CoffeeCoinUseCases,
    val musicUseCases: MusicUseCases,
) : ViewModel() {

    private val _coffeeCoins = MutableStateFlow<CoffeeCoin?>(null)
    val coffeeCoin: Flow<CoffeeCoin?> = _coffeeCoins

    // Music
    private var mediaPlayer: MediaPlayer? = null

    private var playMusicJob: Job? = null

    private val _musicList = MutableStateFlow<List<Music>>(emptyList())
    val musicList: Flow<List<Music>> = _musicList

    private val _selectedPlayMusic = MutableStateFlow(0)
    val selectedPlayMusic: Flow<Int> = _selectedPlayMusic

    // Tag
    private val _tagList = MutableStateFlow<List<Tag>>(emptyList())
    val tagList: Flow<List<Tag>> = _tagList

    private val _availableTag = MutableStateFlow<List<Tag>>(emptyList())
    val availableTag: Flow<List<Tag>> = _availableTag

    // navBar
    private val _selectedNavigationBottomBarIndex = MutableStateFlow(0)

    val selectedNavigationBottomBarIndex: Flow<Int> = _selectedNavigationBottomBarIndex

    // Timer
    private val _isOpenTimer = MutableStateFlow(false)
    val isTimerRunning: Flow<Boolean> = _isOpenTimer

    private val _selectedTag = MutableStateFlow<Tag?>(null)
    val selectedTag: Flow<Tag?> = _selectedTag

    private val _selectedTime = MutableStateFlow(0)
    val selectedTime: Flow<Int> = _selectedTime


    // dialogs
    private val _isOpenDialogBalance = MutableStateFlow(false)
    val isOpenDialogBalance: Flow<Boolean> = _isOpenDialogBalance

    private val _isOpenDialogTagCollection = MutableStateFlow(false)
    val isOpenDialogTagCollection: Flow<Boolean> = _isOpenDialogTagCollection

    private val _isOpenDialogMusicCollection = MutableStateFlow(false)
    val isOpenDialogMusicCollection: Flow<Boolean> = _isOpenDialogMusicCollection


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
    }

    fun setTimer() {
        _isOpenTimer.value = !_isOpenTimer.value
    }

    fun setSelectedNavigationBottomBarIndex(index: Int) {
        _selectedNavigationBottomBarIndex.value = index
    }

    fun setSelectedOnTeenSecondsMusic(musicResId: Int) {
        if (musicResId == _selectedPlayMusic.value) {
            stopMusic()
            return
        }

        stopMusic()

        _selectedPlayMusic.value = musicResId

        playMusicJob = viewModelScope.launch {
            mediaPlayer = MediaPlayer.create(context, musicResId)
            mediaPlayer?.start()

            delay(10_000)

            stopMusic()
        }

        _selectedTag.value = tagCollection.tagList[8]
    }

    fun stopMusic() {
        playMusicJob?.cancel()
        playMusicJob = null

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

        _selectedPlayMusic.value = 0
    }

    fun setDialogBalance() {
        _isOpenDialogBalance.value = !_isOpenDialogBalance.value
    }

    fun setDialogMusicStore(turn: Boolean) {
        _isOpenDialogMusicCollection.value = turn
    }

    fun setDialogTagCollection(turn: Boolean) {
        _isOpenDialogTagCollection.value = turn
    }

    fun setSelectedTag(tag: Tag) {
        _selectedTag.value = tag
    }

    suspend fun updateBalance(balance: Int) {
        coffeeCoinUseCases.updateBalanceUseCase(balance)
    }

    suspend fun buyTag(tag: Tag): Boolean {
        _coffeeCoins.value?.let { coffeeCoin ->
            if (tag.price > coffeeCoin.balance) return false

            tagUseCases.updateTagUseCase(tag)
            return true
        }
        return false
    }

    suspend fun buyMusic(music: Music): Boolean {
        if (music.price > (_coffeeCoins.value?.balance ?: 0)) return false

        _musicList.value.let {
            _musicList.value = it.map {
                if (it.title == music.title) {
                    it.copy(status = ItemStatus.AVAILABLE)
                } else {
                    it
                }
            }
            return true
        }
    }
}