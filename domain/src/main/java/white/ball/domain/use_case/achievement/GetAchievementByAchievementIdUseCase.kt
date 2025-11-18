package white.ball.domain.use_case.achievement

import white.ball.domain.model.Achievement
import white.ball.domain.repository.AchievementRepository
import javax.inject.Inject

class GetAchievementByAchievementIdUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository
) {
    suspend operator fun invoke(id: Long): Achievement {
        return achievementRepository.getAchievementByAchievementId(id)
    }
}