package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
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

    @Transaction
    @Query("SELECT * FROM note WHERE noteId = :id")
    fun getNoteWithTasksById(id: Long): Flow<NoteWithTasksDTO>

    @Query("SELECT * FROM TaskDTO WHERE noteId = :noteId")
    fun getTaskListByNoteId(noteId: Long): List<TaskDTO>

    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun insertNote(note: NoteDTO): Long

    @Insert(onConflict = REPLACE)
    suspend fun insertTask(task: TaskDTO)

    @Insert(onConflict = REPLACE)
    suspend fun insertTaskList(taskList: List<TaskDTO>)


    @Delete
    suspend fun deleteTask(task: TaskDTO)

    @Delete
    suspend fun deleteNote(note: NoteDTO)

    @Update
    suspend fun editNote(note: NoteDTO)

}