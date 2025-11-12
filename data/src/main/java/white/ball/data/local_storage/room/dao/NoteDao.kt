package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.agregate.NoteWithTasksDTO

@Dao
interface NoteDao {

    @Transaction
    @Query("SELECT * FROM note")
    fun getNoteListWithTasks(): Flow<List<NoteWithTasksDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteDTO): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(taskList: List<TaskDTO>)

    @Delete
    suspend fun deleteNote(note: NoteDTO)


    @Query("DELETE FROM TaskDTO WHERE noteId = :noteId")
    suspend fun deleteTasksByNoteId(noteId: Long)

    @Update
    suspend fun editNote(note: NoteDTO)

}