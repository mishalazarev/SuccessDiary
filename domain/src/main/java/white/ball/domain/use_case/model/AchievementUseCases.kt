package white.ball.domain.use_case.model

import white.ball.domain.use_case.achievement.DeleteAchievementUseCase
import white.ball.domain.use_case.achievement.GetAchievementByAchievementIdUseCase
import white.ball.domain.use_case.achievement.GetAchievementListUseCase
import white.ball.domain.use_case.achievement.GetAchievementTaskListByIdUseCase
import white.ball.domain.use_case.achievement.InsertAchievementListUseCase
import white.ball.domain.use_case.achievement.InsertAchievementTaskListUseCase
import white.ball.domain.use_case.achievement.InsertAchievementUseCase
import white.ball.domain.use_case.note.InsertTaskListUseCase
import javax.inject.Inject

class AchievementUseCases @Inject constructor(
    val getAchievementListUseCase: GetAchievementListUseCase,
    val getAchievementByAchievementIdUseCase: GetAchievementByAchievementIdUseCase,
    val getAchievementTaskListByIdUseCase: GetAchievementTaskListByIdUseCase,
    val insertAchievementListUseCase: InsertAchievementListUseCase,
    val insertAchievementUseCase: InsertAchievementUseCase,
    val insertAchievementTaskListUseCase: InsertAchievementTaskListUseCase,
    val deleteAchievementUseCase: DeleteAchievementUseCase,
)