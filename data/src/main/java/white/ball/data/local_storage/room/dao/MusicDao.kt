package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.MusicDTO

@Dao
interface MusicDao {

    @Query("SELECT * from MusicDTO")
    fun getMusicList(): Flow<List<MusicDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun insertMusicList(musicList: List<MusicDTO>)

    @Insert(onConflict = REPLACE)
    suspend fun insertMusic(musicDTO: MusicDTO)

    @Update(onConflict = REPLACE)
    suspend fun updateMusic(musicDTO: MusicDTO)
}