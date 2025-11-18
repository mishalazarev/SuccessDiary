package white.ball.domain.use_case.achievement

import white.ball.domain.model.Achievement
import white.ball.domain.repository.AchievementRepository
import javax.inject.Inject

class DeleteAchievementUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository
) {

    suspend operator fun invoke(achievement: Achievement) {
        achievementRepository.deleteAchievement(achievement)
    }
}