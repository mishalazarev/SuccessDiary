package white.ball.domain.use_case.model

import white.ball.domain.use_case.user.CreateUserUseCase
import white.ball.domain.use_case.user.DeleteUserUseCase
import white.ball.domain.use_case.user.GetUserUseCase
import white.ball.domain.use_case.user.UpdateUserUseCase
import javax.inject.Inject

class UserUseCases @Inject constructor(
    val getUserUseCase: GetUserUseCase,
    val createUserUseCase: CreateUserUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val deleteUserUseCase: DeleteUserUseCase
)