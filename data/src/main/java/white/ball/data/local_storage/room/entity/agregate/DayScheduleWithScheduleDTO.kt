package white.ball.data.local_storage.room.entity.agregate

import androidx.room.Embedded
import androidx.room.Relation
import white.ball.data.local_storage.room.entity.DayScheduleDTO
import white.ball.data.local_storage.room.entity.additional.ScheduleDTO
import white.ball.data.local_storage.room.entity.additional.toScheduleDomainModel
import white.ball.domain.model.DayScheduleDomainModel

data class DayScheduleWithScheduleDTO (
    @Embedded
    val daySchedule: DayScheduleDTO,

    @Relation(
        parentColumn = "dayId",
        entityColumn = "dayId"
    )
    val scheduleList: List<ScheduleDTO>
)

fun DayScheduleWithScheduleDTO.toDayScheduleDomainModel() = DayScheduleDomainModel(
    dayId = daySchedule.dayId,
    createdDate = daySchedule.createdDate,
    scheduleList = this.scheduleList.map { it.toScheduleDomainModel() }
)

