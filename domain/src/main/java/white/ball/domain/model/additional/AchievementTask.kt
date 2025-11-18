package white.ball.domain.model.additional

data class AchievementTask (
    val achievementId: Long,
    val achievementTaskId: Long,
    val title: String,
    var isCompleted: Boolean = false,
    val reward: Int
)