package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.additional.TagDTO

@Dao
interface TagDao {

    @Query("SELECT * FROM tag")
    fun getTagList(): Flow<List<TagDTO>>

    @Insert
    suspend fun addTag(tagDTO: TagDTO)

    @Update
    suspend fun updateTag(tagDTO: TagDTO)

    @Delete
    suspend fun deleteTag(tagDTO: TagDTO)
}