package white.ball.domain.use_case.time

import white.ball.domain.model.FocusTime
import white.ball.domain.repository.FocusTimeRepository
import javax.inject.Inject

class DeleteFocusTimeUseCase @Inject constructor(
    private val focusTimeRepository: FocusTimeRepository
) {

    suspend operator fun invoke(focusTime: FocusTime) {
        focusTimeRepository.deleteFocusTime(focusTime)
    }
}