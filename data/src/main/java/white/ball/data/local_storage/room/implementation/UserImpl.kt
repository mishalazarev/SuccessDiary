package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.UserDao
import white.ball.data.local_storage.room.util.mapper.toUserDTO
import white.ball.data.local_storage.room.util.mapper.toUserFullStack
import white.ball.domain.model.User
import white.ball.domain.model.UserFullStack
import white.ball.domain.repository.UserRepository
import javax.inject.Inject

class UserImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun getUser(): Flow<UserFullStack> {
        return userDao.getUser().map { it.toUserFullStack() }
    }

    override suspend fun addUser(user: User) {
        val userDTO = user.toUserDTO()
        userDao.addUser(userDTO)
    }

    override suspend fun updateUser(user: User) {
        val userDTO = user.toUserDTO()
        userDao.updateUser(userDTO)
    }

    override suspend fun deleteUser(user: User) {
        val userDTO = user.toUserDTO()
        userDao.deleteUser(userDTO)
    }

}