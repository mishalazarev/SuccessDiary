package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.AchievementDao
import white.ball.data.local_storage.room.util.mapper.toAchievement
import white.ball.data.local_storage.room.util.mapper.toAchievementTDO
import white.ball.data.local_storage.room.util.mapper.toAchievementTask
import white.ball.data.local_storage.room.util.mapper.toAchievementTaskDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.additional.AchievementTask
import white.ball.domain.repository.AchievementRepository
import javax.inject.Inject


class AchievementImpl @Inject constructor(
    private val achievementDao: AchievementDao
) : AchievementRepository {

    override fun getAchievementList(): Flow<List<Achievement>?> {
        return achievementDao.getAchievementList().map { list ->
            list?.map {
                it.toAchievement()
            }
        }
    }

    override suspend fun getAchievementTaskListByAchievementId(achievementId: Long): List<AchievementTask> {
        return achievementDao.getAchievementTaskListById(achievementId).map {
            it.toAchievementTask()
        }
    }

    override suspend fun getAchievementByAchievementId(achievementId: Long): Achievement {
        val achievementDTO = achievementDao.getAchievementById(achievementId)
        val achievementTask = achievementDao.getAchievementTaskListById(achievementId)

        return achievementDTO.toAchievement(achievementTask)
    }

    override suspend fun insertAchievementList(achievementList: List<Achievement>) {
        achievementDao.insertAchievementList(
            achievementList.map {
                it.toAchievementTDO()
            }
        )
    }

    override suspend fun insertAchievement(achievement: Achievement): Long {
        return achievementDao.insertAchievement(achievement.toAchievementTDO())
    }

    override suspend fun insertAchievementTaskList(achievementTaskList: List<AchievementTask>) {
        achievementDao.insertAchievementTaskList(
            achievementTaskList.map {
                it.toAchievementTaskDTO()
            }
        )
    }

    override suspend fun updateAchievement(achievement: Achievement) {
        achievementDao.editAchievement(achievement.toAchievementTDO())
    }

    override suspend fun deleteAchievement(achievement: Achievement) {
        achievementDao.deleteAchievement(achievement.toAchievementTDO())
    }
}