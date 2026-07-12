package white.ball.domain.model

import white.ball.domain.model.additional.ScheduleDomainModel
import java.time.LocalDate

data class DayScheduleDomainModel(
    val dayId: Long,
    val createdDate: LocalDate,
    val scheduleList: List<ScheduleDomainModel>
)

