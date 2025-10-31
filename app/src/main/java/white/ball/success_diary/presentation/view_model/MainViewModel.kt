package white.ball.success_diary.presentation.view_model

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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

    val user: Flow<UserFullStack?> = userUseCases.getUserUseCase()

    val tags = tagUseCases.getTagListUseCase()

    suspend fun createUser(user: User) {
        userUseCases.createUserUseCase(user)
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