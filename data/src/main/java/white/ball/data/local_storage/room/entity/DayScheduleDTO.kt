package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.additional.ScheduleDTO
import white.ball.data.local_storage.room.entity.additional.toScheduleByDayScheduleDTO
import white.ball.data.local_storage.room.entity.additional.toScheduleDomainModel
import white.ball.domain.model.DayScheduleDomainModel
import java.time.LocalDate

@Entity
data class DayScheduleDTO(
    @PrimaryKey(autoGenerate = true)
    val dayId: Long,
    val createdDate: LocalDate,
    val scheduleList: List<ScheduleDTO>
)

fun DayScheduleDomainModel.toDayScheduleDTO() = DayScheduleDTO(
    dayId = this.dayId,
    createdDate = this.createdDate,
    scheduleList = this.scheduleList.map { it.toScheduleByDayScheduleDTO(this.dayId) }
)

fun DayScheduleDTO.toDayScheduleDomainModel() = DayScheduleDomainModel(
    dayId = this.dayId,
    createdDate = this.createdDate,
    scheduleList = this.scheduleList.map { it.toScheduleDomainModel() }
)