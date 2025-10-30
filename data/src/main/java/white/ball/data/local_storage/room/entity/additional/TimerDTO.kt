package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.main.UserDTO

@Entity("timer",
    foreignKeys = [ForeignKey(
        entity = UserDTO::class,
        parentColumns = ["userId"],
        childColumns = ["userOwnerId"],
        onDelete = ForeignKey.CASCADE
    )])
data class TimerDTO(
    @PrimaryKey(autoGenerate = true)
    val timerId: Long,
    val maxTime: Long,
    var leftTime: Long,
    val userOwnerId: Long,
)
