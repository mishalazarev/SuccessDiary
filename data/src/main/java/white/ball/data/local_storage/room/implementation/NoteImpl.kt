package white.ball.data.local_storage.room.implementation

import android.util.Log
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.NoteDao
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.util.mapper.toNote
import white.ball.data.local_storage.room.util.mapper.toNoteDTO
import white.ball.data.local_storage.room.util.mapper.toTaskDTO
import white.ball.data.local_storage.room.util.mapper.toTaskDomainModel
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.repository.NoteRepository
import javax.inject.Inject

class NoteImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getNoteList(): Flow<List<NoteDomainModel>> {
        return noteDao.getNoteListWithTasks().map { list ->
            list.map { it.toNote() }
        }
    }

    override suspend fun addNote(noteModelUI: NoteDomainModel): Long {
        val noteDTO = noteModelUI.toNoteDTO()
        val newNoteId = noteDao.insertNote(noteDTO)
        val tasksDTO: List<TaskDTO> = noteModelUI.taskList.map { task: TaskByNoteDomainModel -> task.toTaskDTO(newNoteId) }
        noteDao.insertTaskList(tasksDTO)
        Log.e("tag", "addNote: ${noteModelUI.taskList.size}", )
        return noteDTO.noteId
    }

    override suspend fun editNote(noteModelUI: NoteDomainModel) {
        val noteDTO = noteModelUI.toNoteDTO()
        noteDao.insertNote(noteDTO)
        val tasksDTO: List<TaskDTO> = noteModelUI.taskList.map { task: TaskByNoteDomainModel -> task.toTaskDTO(noteModelUI.noteId) }
        noteDao.insertTaskList(tasksDTO)
    }

    @Insert(onConflict = REPLACE)
    override suspend fun insertTask(taskByNoteDomainModel: TaskByNoteDomainModel) {
        noteDao.insertTask(taskByNoteDomainModel.toTaskDTO())
    }

    override fun getNoteWithTasksById(noteId: Long): Flow<NoteDomainModel> {
        return noteDao.getNoteWithTasksById(noteId).map { it.toNote() }
    }

    override fun getTaskListByNoteId(noteId: Long): List<TaskByNoteDomainModel> {
        return noteDao.getTaskListByNoteId(noteId).map { it.toTaskDomainModel()  }
    }

    override suspend fun deleteNote(noteModelUI: NoteDomainModel) {
        val noteDTO = noteModelUI.toNoteDTO()
        noteDao.deleteNote(noteDTO)
    }

    override suspend fun deleteTask(taskByNoteDomainModel: TaskByNoteDomainModel) {
        noteDao.deleteTask(taskByNoteDomainModel.toTaskDTO())
    }

    override suspend fun insertTaskList(taskByNoteDomainModel: List<TaskByNoteDomainModel>) {
        noteDao.insertTaskList(taskByNoteDomainModel.map { it.toTaskDTO() })
    }
}