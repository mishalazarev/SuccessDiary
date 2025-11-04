package white.ball.success_diary.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import white.ball.domain.model.Tag
import white.ball.domain.model.User
import white.ball.domain.model.UserFullStack
import white.ball.domain.use_case.model.TagUseCases
import white.ball.domain.use_case.model.UserUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val tagUseCases: TagUseCases,
) : ViewModel() {

    private val _user = MutableStateFlow<UserFullStack?>(null)
    val user: StateFlow<UserFullStack?> = _user

    val tags = tagUseCases.getTagListUseCase()

    private val _selectedNavigationBottomBarIndex = MutableStateFlow(0)

    val selectedNavigationBottomBarIndex: Flow<Int> = _selectedNavigationBottomBarIndex

    private val _isOpenTimer = MutableStateFlow(false)
    val isTimerRunning: Flow<Boolean> = _isOpenTimer

    private val _isOpenDialogBalance = MutableStateFlow(false)
    val isOpenDialogBalance: Flow<Boolean> = _isOpenDialogBalance

    init {
        viewModelScope.launch {
            userUseCases.getUserUseCase().collect { userFullStack ->
                if (userFullStack == null) {
                    userUseCases.createUserUseCase(User())
                }
                _user.value = userFullStack
            }
        }
    }

    fun setTimer() {
        _isOpenTimer.value = !_isOpenTimer.value
    }

    fun setSelectedNavigationBottomBarIndex(index: Int) {
        _selectedNavigationBottomBarIndex.value = index
    }

    fun setDialogBalance() {
        _isOpenDialogBalance.value = !_isOpenDialogBalance.value
    }

    fun getUser() {
        viewModelScope.launch (Dispatchers.IO) {
            if (user.first() == null) {
                userUseCases.createUserUseCase(
                    User()
                )
            }
        }
    }

    suspend fun deleteUser(user: User) {
        userUseCases.deleteUserUseCase(user)
    }

    suspend fun addTag(tag: Tag) {
        tagUseCases.createTagUseCase(tag)
    }

    suspend fun updateUser(user: User) {
        userUseCases.updateUserUseCase(user)
    }
}