package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.additional.ScheduleDTO
import white.ball.data.local_storage.room.entity.additional.toScheduleByDayScheduleDTO
import white.ball.data.local_storage.room.entity.additional.toScheduleDomainModel
import white.ball.domain.model.DayScheduleDomainModel
import white.ball.domain.model.additional.ScheduleDomainModel
import java.time.LocalDate

@Entity
data class DayScheduleDTO(
    @PrimaryKey(autoGenerate = true)
    val dayId: Long,
    val createdDate: LocalDate,
)

fun DayScheduleDomainModel.toDayScheduleDTO() = DayScheduleDTO(
    dayId = this.dayId,
    createdDate = this.createdDate,
)
