package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Task

interface TaskRepository {
    fun getTaskList(): Flow<List<Task>>

    suspend fun addTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun updateTask(task: Task)
}