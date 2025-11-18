package white.ball.domain.model

import white.ball.domain.extension_model.AchievementMeasurement
import white.ball.domain.model.additional.AchievementTask


data class Achievement(
    val achievementId: Long,
    val title: String,
    val imageResId: Int,
    val measurement: AchievementMeasurement,
    var current: Int,
    val achievementTaskList: List<AchievementTask>
)
