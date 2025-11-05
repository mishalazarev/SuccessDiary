package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.NoteDTO

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNoteList(): Flow<List<NoteDTO>>

    @Insert
    suspend fun addNote(note: NoteDTO)

    @Delete
    suspend fun deleteNote(note: NoteDTO)

    @Update
    suspend fun editNote(note: NoteDTO)

}