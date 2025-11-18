package white.ball.domain.use_case.achievement

import white.ball.domain.model.Achievement
import white.ball.domain.repository.AchievementRepository
import javax.inject.Inject

class InsertAchievementListUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository
) {

    suspend operator fun invoke(achievementList: List<Achievement>) {
        achievementRepository.insertAchievementList(achievementList)
    }
}