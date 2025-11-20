package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.ItemStatus

@Entity(tableName = "tag",)
data class TagDTO(
    @PrimaryKey(autoGenerate = true)
    val tagId: Long = 0,
    val title: String,
    val imageResId: Int,
    val status: ItemStatus,
    val price: Int,
)

