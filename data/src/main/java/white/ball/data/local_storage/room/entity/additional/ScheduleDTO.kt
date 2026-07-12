package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import white.ball.data.local_storage.room.entity.DayScheduleDTO
import white.ball.domain.model.additional.ScheduleDomainModel
import java.time.LocalDate

@Entity (
    foreignKeys = [
        ForeignKey(
            entity = DayScheduleDTO::class,
            parentColumns = ["dayId"],
            childColumns = ["dayId"],
            onDelete = CASCADE
        )
    ]
)
data class ScheduleDTO(
    val scheduleId: Long,
    val dayId: Long,
    var tagTime: Long,
    var tagCount: Int,
    val tagImageResId: Int,
)

fun ScheduleDTO.toScheduleDomainModel() = ScheduleDomainModel(
    scheduleId = this.scheduleId,
    dayId = this.dayId,
    tagTime = this.tagTime,
    tagCount = this.tagCount,
    tagImageResId = this.tagImageResId,
    createDate = LocalDate.now()
)

fun ScheduleDomainModel.toScheduleByDayScheduleDTO() = ScheduleDTO(
    scheduleId = this.scheduleId,
    dayId = this.dayId,
    tagTime = this.tagTime,
    tagCount = this.tagCount,
    tagImageResId = this.tagImageResId,
)

fun ScheduleDomainModel.toScheduleByDayScheduleDTO(dayId: Long) = ScheduleDTO(
    scheduleId = this.scheduleId,
    dayId = dayId,
    tagTime = this.tagTime,
    tagCount = this.tagCount,
    tagImageResId = this.tagImageResId,
)