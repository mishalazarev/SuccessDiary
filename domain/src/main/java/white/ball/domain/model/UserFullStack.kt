package white.ball.domain.model

data class UserFullStack(
    val user: User,
    val taskList: List<Task>,
    val noteModelUIList: List<NoteDomainModel>,
    val achievementList: List<Achievement>,
    val tagList: List<Tag>,
)
