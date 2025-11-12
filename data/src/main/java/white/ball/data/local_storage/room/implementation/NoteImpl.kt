package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import white.ball.data.local_storage.room.dao.NoteDao
import white.ball.data.local_storage.room.entity.agregate.NoteWithTasksDTO
import white.ball.data.local_storage.room.util.mapper.toNote
import white.ball.data.local_storage.room.util.mapper.toNoteDTO
import white.ball.data.local_storage.room.util.mapper.toNoteWithTasksDTO
import white.ball.data.local_storage.room.util.mapper.toTaskDTO
import white.ball.domain.model.NoteDomainModel
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
        val noteId = noteDao.insertNote(noteDTO)

        val tasksDTO = noteModelUI.taskList.map { it.copy(noteId = noteId).toTaskDTO(noteId) }
        if (tasksDTO.isNotEmpty()) {
            noteDao.insertTaskList(tasksDTO)
        }

        return noteId
    }

    override suspend fun editNote(noteModelUI: NoteDomainModel) {
        val noteDTO = noteModelUI.toNoteDTO()

        noteDao.editNote(noteDTO)
        noteDao.deleteTasksByNoteId(noteModelUI.noteId)

        val newTasksDTO = noteModelUI.taskList.map {
            it.copy(noteId = noteModelUI.noteId).toTaskDTO(noteModelUI.noteId)
        }
        if (newTasksDTO.isNotEmpty()) {
            noteDao.insertTaskList(newTasksDTO)
        }
    }

    override suspend fun deleteNote(noteModelUI: NoteDomainModel) {
        val noteDTO = noteModelUI.toNoteDTO()
        noteDao.deleteNote(noteDTO)
    }
}