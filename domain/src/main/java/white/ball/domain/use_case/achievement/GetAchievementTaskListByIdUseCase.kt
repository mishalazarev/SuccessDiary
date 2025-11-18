package white.ball.domain.use_case.achievement

import white.ball.domain.model.additional.AchievementTask
import white.ball.domain.repository.AchievementRepository
import javax.inject.Inject

class GetAchievementTaskListByIdUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository
) {

    suspend operator fun invoke(achievementId: Long): List<AchievementTask> {
        return achievementRepository.getAchievementTaskListByAchievementId(achievementId)
    }
}