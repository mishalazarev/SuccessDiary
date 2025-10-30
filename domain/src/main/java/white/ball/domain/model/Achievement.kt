package white.ball.domain.model

import white.ball.domain.extension_model.AchievementMeasurement


data class Achievement(
    val achievementId: Long,
    val name: String,
    val isDoneTaskList: LinkedHashMap<Int, Boolean>,
    val maxEventList: LinkedHashMap<Int, Int>,
    val measurement: AchievementMeasurement,
    val currentEvent: Int,
    val maxEvent: Int,
    val userOwnerId: Long,
)
