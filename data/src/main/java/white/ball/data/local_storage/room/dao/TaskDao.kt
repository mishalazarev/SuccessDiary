package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.additional.TaskDTO

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTaskList(): Flow<List<TaskDTO>>

    @Insert
    suspend fun addTask(task: TaskDTO)

    @Delete
    suspend fun deleteTask(task: TaskDTO)

    @Update
    suspend fun updateTask(task: TaskDTO)
}