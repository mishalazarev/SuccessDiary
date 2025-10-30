package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.User
import white.ball.domain.model.UserFullStack

interface UserRepository {
    fun getUser(): Flow<UserFullStack>

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)
}