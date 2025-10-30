package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.main.UserDTO
import white.ball.domain.extension_model.TaskLocation

@Entity("task",
    foreignKeys = [ForeignKey(
        entity = UserDTO::class,
        parentColumns = ["userId"],
        childColumns = ["userOwnerId"],
        onDelete = ForeignKey.CASCADE
    )])
data class TaskDTO(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,
    var title: String,
    var color: Int,
    var isDone: Boolean,
    var location: TaskLocation,
    var creationDate: Long,
    val userOwnerId: Long,
)
