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
import white.ball.data.local_storage.room.entity.agregate.DayScheduleWithScheduleDTO
import java.time.LocalDate

@Dao
interface ScheduleDao {

    @Insert(onConflict = REPLACE)
    fun createSchedule(dayScheduleDTO: DayScheduleDTO)

    @Transaction
    @Query("SELECT * FROM DayScheduleDTO")
    fun getScheduleList(): Flow<List<DayScheduleWithScheduleDTO>>

    @Transaction
    @Query("SELECT * FROM DayScheduleDTO WHERE createdDate = :localDate")
    fun getScheduleByDay(localDate: LocalDate): Flow<DayScheduleWithScheduleDTO>

    @Query("DELETE FROM DayScheduleDTO WHERE createdDate = :localDate")
    suspend fun deleteDaySchedule(localDate: LocalDate)

    @Insert(onConflict = REPLACE)
    suspend fun insertDaySchedule(dayScheduleDTO: DayScheduleDTO)

    @Insert
    suspend fun insertSchedule(dayScheduleDTO: DayScheduleDTO)

    @Delete
    suspend fun deleteDaySchedule(dayScheduleDTO: DayScheduleDTO)

    @Update
    suspend fun updateDaySchedule(dayScheduleDTO: DayScheduleDTO)

}