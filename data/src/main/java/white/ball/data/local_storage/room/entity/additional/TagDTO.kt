package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.main.UserDTO
import white.ball.domain.extension_model.TagStatus

@Entity(
    tableName = "tag",
    foreignKeys = [
        ForeignKey(
            entity = UserDTO::class,
            parentColumns = ["userId"],
            childColumns = ["userOwnerId"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class TagDTO(
    @PrimaryKey(autoGenerate = true)
    val tagId: Long = 0,
    val title: String,
    val status: TagStatus,
    val timer: TimerDTO,
    val price: Int,
    val userOwnerId: Long,
)

