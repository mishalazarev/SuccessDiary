package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.DayScheduleDTO
import white.ball.data.local_storage.room.entity.additional.ScheduleDTO
import java.time.LocalDate

@Dao
interface ScheduleDao {

    @Insert(onConflict = REPLACE)
    fun createSchedule(dayScheduleDTO: DayScheduleDTO)

    @Transaction
    @Query("SELECT * FROM DayScheduleDTO")
    fun getScheduleList(): Flow<List<DayScheduleDTO>>

    @Transaction
    @Query("SELECT * FROM DayScheduleDTO WHERE dayId = :localDate")
    fun getScheduleByDay(localDate: LocalDate): Flow<DayScheduleDTO>

    @Query("DELETE FROM DayScheduleDTO WHERE createdDate = :localDate")
    suspend fun deleteDaySchedule(localDate: LocalDate)

    @Insert(onConflict = REPLACE)
    suspend fun insertDaySchedule(dayScheduleDTO: DayScheduleDTO)

    @Delete
    suspend fun deleteDaySchedule(dayScheduleDTO: DayScheduleDTO)

    @Update
    suspend fun updateDaySchedule(dayScheduleDTO: DayScheduleDTO)
}