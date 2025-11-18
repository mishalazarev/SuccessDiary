package white.ball.domain.use_case.achievement

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Achievement
import white.ball.domain.repository.AchievementRepository
import javax.inject.Inject

class GetAchievementListUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository
) {
    operator fun invoke(): Flow<List<Achievement>?> {
        return achievementRepository.getAchievementList()
    }
}