package white.ball.domain.use_case.note

import white.ball.domain.model.Note
import white.ball.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.addNote(note)
    }
}