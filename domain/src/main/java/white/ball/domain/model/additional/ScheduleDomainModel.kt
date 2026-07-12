package white.ball.domain.model.additional

data class ScheduleDomainModel(
    var scheduleId: Long,
    var dayId: Long = 0,
    var tagTime: Long = 0,
    var tagCount: Int = 0,
    val tagImageResId: Int = 0,
)
