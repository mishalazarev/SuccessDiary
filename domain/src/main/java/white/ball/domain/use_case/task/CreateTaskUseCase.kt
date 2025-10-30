package white.ball.domain.use_case.task

import white.ball.domain.model.Task
import white.ball.domain.repository.TaskRepository
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor (
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        taskRepository.addTask(task)
    }
}