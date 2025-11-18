package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Achievement
import white.ball.domain.model.additional.AchievementTask

interface AchievementRepository {

    fun getAchievementList(): Flow<List<Achievement>?>

    suspend fun getAchievementTaskListByAchievementId(achievementId: Long): List<AchievementTask>

    suspend fun getAchievementByAchievementId(achievementId: Long): Achievement

    suspend fun insertAchievementList(achievementList: List<Achievement>)

    suspend fun insertAchievement(achievement: Achievement): Long

    suspend fun insertAchievementTaskList(achievementTaskList: List<AchievementTask>)

    suspend fun updateAchievement(achievement: Achievement)

    suspend fun deleteAchievement(achievement: Achievement)
}