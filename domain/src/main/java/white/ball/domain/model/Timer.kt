package white.ball.domain.model

data class Timer(
    val timerId: Long,
    val maxTime: Long,
    var lefTime: Long,
    val userOwnerId: Long,
)
