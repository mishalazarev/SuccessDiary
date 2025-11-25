package white.ball.domain.use_case.model

import white.ball.domain.use_case.time.DeleteFocusTimeUseCase
import white.ball.domain.use_case.time.GetFocusTimeUseCase
import white.ball.domain.use_case.time.InsertFocusTimeUseCase
import javax.inject.Inject

class FocusTimeUseCases @Inject constructor(
    val getFocusTimeUseCase: GetFocusTimeUseCase,
    val insertFocusTimeUseCase: InsertFocusTimeUseCase,
    val deleteFocusTimeUseCase: DeleteFocusTimeUseCase,
)