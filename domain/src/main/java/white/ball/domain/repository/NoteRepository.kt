package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Note

interface NoteRepository {
    fun getNoteList(): Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun editNote(note: Note)
}