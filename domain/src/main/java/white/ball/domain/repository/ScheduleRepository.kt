package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.DayScheduleDomainModel
import white.ball.domain.model.additional.ScheduleDomainModel
import java.time.LocalDate

interface ScheduleRepository {

    suspend fun createSchedule(dayScheduleDomainModel: DayScheduleDomainModel)

    fun getScheduleList(): Flow<List<DayScheduleDomainModel>>

    suspend fun updateSchedule(dayScheduleDomainModel: DayScheduleDomainModel)

    suspend fun deleteDaySchedule(dayScheduleDomainModel: DayScheduleDomainModel)

    suspend fun getDaySchedule(localDate: LocalDate): Flow<DayScheduleDomainModel>

    suspend fun insertDaySchedule(dayScheduleDomainModel: DayScheduleDomainModel)

    suspend fun updateDaySchedule(dayScheduleDomainModel: DayScheduleDomainModel)
}