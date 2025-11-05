package white.ball.success_diary.presentation.view_model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import white.ball.domain.model.Tag
import white.ball.domain.use_case.model.TagUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tagUseCases: TagUseCases,
) : ViewModel() {

    val tags = tagUseCases.getTagListUseCase()

    private val _selectedNavigationBottomBarIndex = MutableStateFlow(0)

    val selectedNavigationBottomBarIndex: Flow<Int> = _selectedNavigationBottomBarIndex

    private val _isOpenTimer = MutableStateFlow(false)
    val isTimerRunning: Flow<Boolean> = _isOpenTimer

    private val _isOpenDialogBalance = MutableStateFlow(false)
    val isOpenDialogBalance: Flow<Boolean> = _isOpenDialogBalance

    fun setTimer() {
        _isOpenTimer.value = !_isOpenTimer.value
    }

    fun setSelectedNavigationBottomBarIndex(index: Int) {
        _selectedNavigationBottomBarIndex.value = index
    }

    fun setDialogBalance() {
        _isOpenDialogBalance.value = !_isOpenDialogBalance.value
    }

    suspend fun addTag(tag: Tag) {
        tagUseCases.createTagUseCase(tag)
    }
}