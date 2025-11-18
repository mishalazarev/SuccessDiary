package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.AchievementMeasurement


@Entity
data class AchievementDTO(
    @PrimaryKey(autoGenerate = true)
    val achievementId: Long,
    val title: String,
    val imageResId: Int,
    val measurement: AchievementMeasurement,
    var current: Int,
)
