package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.ItemStatus

@Entity
data class MusicDTO(
    @PrimaryKey(autoGenerate = true)
    val musicId: Long,
    val title: String,
    val artist: String,
    val rawResId: Int,
    val imageResId: Int,
    val price: Int,
    var status: ItemStatus,
)
