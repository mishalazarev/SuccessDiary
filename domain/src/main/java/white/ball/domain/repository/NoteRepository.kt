package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.NoteDomainModel

interface NoteRepository {
    fun getNoteList(): Flow<List<NoteDomainModel>>

    suspend fun addNote(noteModelUI: NoteDomainModel): Long

    suspend fun deleteNote(noteModelUI: NoteDomainModel)

    suspend fun editNote(noteModelUI: NoteDomainModel)
}