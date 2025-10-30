package white.ball.domain.model

data class UserFullStack(
    val user: User,
    val taskList: List<Task>,
    val noteList: List<Note>,
    val achievementList: List<Achievement>,
    val tagList: List<Tag>,
)
