package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.ScheduleDao
import white.ball.data.local_storage.room.entity.toDayScheduleDTO
import white.ball.data.local_storage.room.entity.toDayScheduleDomainModel
import white.ball.domain.model.DayScheduleDomainModel
import white.ball.domain.repository.ScheduleRepository
import java.time.LocalDate
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {

    override suspend fun createSchedule(dayScheduleDomainModel: DayScheduleDomainModel) {
        scheduleDao.createSchedule(dayScheduleDomainModel.toDayScheduleDTO())
    }

    override fun getScheduleList(): Flow<List<DayScheduleDomainModel>> {
        return scheduleDao.getScheduleList().map { list ->
            list.map { dto -> dto.toDayScheduleDomainModel() }
        }
    }

    override suspend fun updateSchedule(dayScheduleDomainModel: DayScheduleDomainModel) {
        scheduleDao.updateDaySchedule(dayScheduleDomainModel.toDayScheduleDTO())
    }

    override suspend fun deleteDaySchedule(dayScheduleDomainModel: DayScheduleDomainModel) {
        scheduleDao.deleteDaySchedule(dayScheduleDomainModel.toDayScheduleDTO())
    }

    override suspend fun getDaySchedule(localDate: LocalDate): Flow<DayScheduleDomainModel> {
        return scheduleDao.getScheduleByDay(localDate).map { it.toDayScheduleDomainModel() }
    }

    override suspend fun insertDaySchedule(dayScheduleDomainModel: DayScheduleDomainModel) {
        scheduleDao.insertDaySchedule(dayScheduleDomainModel.toDayScheduleDTO())
    }

    override suspend fun updateDaySchedule(dayScheduleDomainModel: DayScheduleDomainModel) {
        scheduleDao.updateDaySchedule(dayScheduleDomainModel.toDayScheduleDTO())

    }

}