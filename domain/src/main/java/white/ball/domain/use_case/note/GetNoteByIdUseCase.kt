package white.ball.domain.use_case.note

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    operator fun invoke(noteId: Long): Flow<NoteDomainModel> {
        return noteRepository.getNoteWithTasksById(noteId)
    }
}