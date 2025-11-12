package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.additional.TimerDTO
import white.ball.domain.extension_model.TagStatus

@Entity(tableName = "tag",)
data class TagDTO(
    @PrimaryKey(autoGenerate = true)
    val tagId: Long = 0,
    val title: String,
    val status: TagStatus,
    val timer: TimerDTO,
    val price: Int,
)

