package white.ball.domain.use_case.user

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.UserFullStack
import white.ball.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<UserFullStack> {
        return userRepository.getUser()
    }
}