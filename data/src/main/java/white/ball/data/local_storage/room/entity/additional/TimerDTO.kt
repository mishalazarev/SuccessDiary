package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("timer",)
data class TimerDTO(
    @PrimaryKey(autoGenerate = true)
    val timerId: Long,
    val maxTime: Long,
    var leftTime: Long,
)