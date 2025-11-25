package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.FocusTimeDTO

@Dao
interface FocusTimeDao {

    @Query("SELECT * FROM FocusTimeDTO")
    fun getFocusTime(): Flow<FocusTimeDTO?>

    @Delete
    fun deleteFocusTime(focusTime: FocusTimeDTO)

    @Insert(onConflict = REPLACE)
    fun insertFocusTime(focusTime: FocusTimeDTO)


}