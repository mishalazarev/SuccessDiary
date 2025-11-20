package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.TagDTO

@Dao
interface TagDao {

    @Query("SELECT * FROM tag")
    fun getTagList(): Flow<List<TagDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTag(tagDTO: TagDTO)

    @Insert(onConflict = REPLACE)
    suspend fun insertTagList(tagList: List<TagDTO>)

    @Update(onConflict = REPLACE)
    suspend fun updateTag(tagDTO: TagDTO)

    @Delete
    suspend fun deleteTag(tagDTO: TagDTO)
}