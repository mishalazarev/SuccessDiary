package white.ball.domain.model.additional

import java.time.LocalDate

data class ScheduleDomainModel(
    var scheduleId: Long,
    val createDate: LocalDate,
    var dayId: Long = 0,
    var tagTime: Long = 0,
    var tagCount: Int = 0,
    val tagImageResId: Int = 0,
)
