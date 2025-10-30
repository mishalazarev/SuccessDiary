package white.ball.domain.use_case.user

import white.ball.domain.model.User
import white.ball.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.addUser(user)
    }
}