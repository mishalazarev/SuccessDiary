package white.ball.domain.model

import white.ball.domain.extension_model.TaskLocation


data class Task(
    val taskId: Long,
    var title: String,
    val color: Int,
    var isDone: Boolean,
    var location: TaskLocation,
    var creationDate: Long,
    val userOwnerId: Long,
)
