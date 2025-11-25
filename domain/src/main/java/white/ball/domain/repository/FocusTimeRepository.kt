package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.FocusTime

interface FocusTimeRepository {

    suspend fun insertFocusTime(focusTime: FocusTime)

    suspend fun deleteFocusTime(focusTime: FocusTime)

    fun getFocusTime(): Flow<FocusTime?>

}