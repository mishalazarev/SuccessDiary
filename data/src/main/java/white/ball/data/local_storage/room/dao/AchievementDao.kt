package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.additional.AchievementTaskDTO
import white.ball.data.local_storage.room.entity.agregate.AchievementWithAchievementTaskDTO

@Dao
interface AchievementDao {
    @Transaction
    @Query("SELECT * FROM AchievementDTO")
    fun getAchievementList(): Flow<List<AchievementWithAchievementTaskDTO>?>

    @Transaction
    @Query("SELECT * FROM ACHIEVEMENTTASKDTO WHERE achievementId = :achievementId")
    suspend fun getAchievementTaskListById(achievementId: Long): List<AchievementTaskDTO>

    @Transaction
    @Query("SELECT * FROM AchievementDTO WHERE achievementId = :achievementId")
    suspend fun getAchievementById(achievementId: Long): AchievementDTO


    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun insertAchievementList(achievementList: List<AchievementDTO>)

    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun insertAchievement(achievement: AchievementDTO): Long

    @Transaction
    @Update(onConflict = REPLACE)
    suspend fun editAchievement(achievement: AchievementDTO)

    @Insert(onConflict = REPLACE)
    suspend fun insertAchievementTaskList(achievementTasksDTO: List<AchievementTaskDTO>)

    @Delete
    suspend fun deleteAchievement(achievement: AchievementDTO)
}