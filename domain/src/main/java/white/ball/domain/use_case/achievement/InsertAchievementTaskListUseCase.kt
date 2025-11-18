package white.ball.domain.use_case.achievement

import white.ball.domain.model.additional.AchievementTask
import white.ball.domain.repository.AchievementRepository
import javax.inject.Inject

class InsertAchievementTaskListUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository
) {

    suspend operator fun invoke(achievementTaskList: List<AchievementTask>) {
        achievementRepository.insertAchievementTaskList(achievementTaskList)
    }
}