package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.AchievementDTO

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievement")
    fun getAchievements(): Flow<List<AchievementDTO>>

    @Insert
    suspend fun addAchievement(achievement: AchievementDTO)

    @Update
    suspend fun editAchievement(achievement: AchievementDTO)

    @Delete
    suspend fun deleteAchievement(achievement: AchievementDTO)
}