package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.main.UserDTO
import white.ball.data.local_storage.room.entity.pack.UserFullStackDTO

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM UserDTO")
    fun getUser(): Flow<UserFullStackDTO>

    @Transaction
    @Insert
    fun addUser(user: UserDTO)

    @Transaction
    @Update
    fun updateUser(user: UserDTO)

    @Transaction
    @Delete
    fun deleteUser(user: UserDTO)
}