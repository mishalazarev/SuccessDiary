package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FocusTimeDTO(
    @PrimaryKey(autoGenerate = true)
    val focusTimeId: Long,
    val focusTime: Int,
)
