package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.FocusTimeDao
import white.ball.data.local_storage.room.util.mapper.toFocusTime
import white.ball.data.local_storage.room.util.mapper.toFocusTimeDTO
import white.ball.domain.model.FocusTime
import white.ball.domain.repository.FocusTimeRepository
import javax.inject.Inject

class FocusTimeImpl @Inject constructor (
    private val focusTimeDao: FocusTimeDao
) : FocusTimeRepository {

    override suspend fun insertFocusTime(focusTime: FocusTime) {
        focusTimeDao.insertFocusTime(
            focusTime.toFocusTimeDTO()
        )
    }

    override suspend fun deleteFocusTime(focusTime: FocusTime) {
        focusTimeDao.deleteFocusTime(
            focusTime.toFocusTimeDTO()
        )
    }

    override fun getFocusTime(): Flow<FocusTime?> {
        return focusTimeDao.getFocusTime().map {
            it?.toFocusTime()
        }
    }
}