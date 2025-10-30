package white.ball.domain.use_case.task

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Task
import white.ball.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getTaskList()
    }
}