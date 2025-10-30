package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.NoteDao
import white.ball.data.local_storage.room.util.mapper.toNote
import white.ball.data.local_storage.room.util.mapper.toNoteDTO
import white.ball.domain.model.Note
import white.ball.domain.repository.NoteRepository
import javax.inject.Inject

class NoteImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getNoteList(): Flow<List<Note>> {
        return noteDao.getNoteList().map { list ->
            list.map { it.toNote() }
        }
    }

    override suspend fun addNote(note: Note) {
        val noteDTO = note.toNoteDTO()
        noteDao.addNote(noteDTO)
    }

    override suspend fun editNote(note: Note) {
        val noteDTO = note.toNoteDTO()
        noteDao.editNote(noteDTO)
    }

    override suspend fun deleteNote(note: Note) {
        val noteDTO = note.toNoteDTO()
        noteDao.deleteNote(noteDTO)
    }
}