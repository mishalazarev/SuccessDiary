package white.ball.domain.use_case.model

import white.ball.domain.use_case.task.CreateTaskUseCase
import white.ball.domain.use_case.task.DeleteTaskUseCase
import white.ball.domain.use_case.task.GetTaskListUseCase
import white.ball.domain.use_case.task.UpdateTaskUseCase
import javax.inject.Inject

class TaskUseCases @Inject constructor(
    val getTaskListUseCase: GetTaskListUseCase,
    val createTaskUseCase: CreateTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
)