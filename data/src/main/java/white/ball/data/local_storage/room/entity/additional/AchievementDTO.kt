package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.main.UserDTO
import white.ball.domain.extension_model.AchievementMeasurement


@Entity("achievement",
    foreignKeys = [
        ForeignKey(
            entity = UserDTO::class,
            parentColumns = ["userId"],
            childColumns = ["userOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userOwnerId"])])
data class AchievementDTO(
    @PrimaryKey(autoGenerate = true)
    val achievementId: Long,
    val name: String,
    val isDoneTaskList: LinkedHashMap<Int, Boolean>,
    val maxEventList: LinkedHashMap<Int, Int>,
    val measurement: AchievementMeasurement,
    val currentEvent: Int,
    val maxEvent: Int,
    val userOwnerId: Long
)
