package white.ball.domain.use_case.time

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.FocusTime
import white.ball.domain.repository.FocusTimeRepository
import javax.inject.Inject

class GetFocusTimeUseCase @Inject constructor(
    private val focusTimeRepository: FocusTimeRepository
) {

    operator fun invoke(): Flow<FocusTime?> {
        return focusTimeRepository.getFocusTime()
    }
}