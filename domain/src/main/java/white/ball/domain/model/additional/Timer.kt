package white.ball.domain.model.additional

data class Timer(
    val timerId: Long,
    val maxTime: Long,
    var lefTime: Long,
)