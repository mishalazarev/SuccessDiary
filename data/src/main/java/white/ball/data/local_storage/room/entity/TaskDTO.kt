package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.TaskLocation

@Entity("task",)
data class TaskDTO(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,
    var title: String,
    var color: Int,
    var isDone: Boolean,
    var location: TaskLocation,
    var dateCreated: String,
)
