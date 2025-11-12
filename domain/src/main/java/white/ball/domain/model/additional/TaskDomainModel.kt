package white.ball.domain.model.additional

import java.util.UUID

data class TaskDomainModel(
    var taskId: Long = 0,
    val localId : String = UUID.randomUUID().leastSignificantBits.toString(),
    var title: String = "",
    var isDone: Boolean = false,
    var noteId: Long = 0,
)