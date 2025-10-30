package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.TaskDao
import white.ball.data.local_storage.room.util.mapper.toTask
import white.ball.data.local_storage.room.util.mapper.toTaskDTO
import white.ball.domain.model.Task
import white.ball.domain.repository.TaskRepository
import javax.inject.Inject

class TaskImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun getTaskList(): Flow<List<Task>> {
        return taskDao.getTaskList().map { list ->
            list.map { it.toTask() }
        }
    }

    override suspend fun addTask(task: Task) {
        val taskDTO = task.toTaskDTO()
        taskDao.addTask(taskDTO)
    }

    override suspend fun updateTask(task: Task) {
        val taskDTO = task.toTaskDTO()
        taskDao.updateTask(taskDTO)
    }

    override suspend fun deleteTask(task: Task) {
        val taskDTO = task.toTaskDTO()
        taskDao.deleteTask(taskDTO)
    }
}