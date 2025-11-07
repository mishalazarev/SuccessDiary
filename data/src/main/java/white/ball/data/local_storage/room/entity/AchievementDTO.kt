package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.AchievementMeasurement


@Entity("achievement")
data class AchievementDTO(
    @PrimaryKey(autoGenerate = true)
    val achievementId: Long,
    val name: String,
    val isDoneTaskList: LinkedHashMap<Int, Boolean>,
    val maxEventList: LinkedHashMap<Int, Int>,
    val measurement: AchievementMeasurement,
    val currentEvent: Int,
    val maxEvent: Int,
)
