package white.ball.domain.use_case.note

import jakarta.inject.Inject
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.repository.NoteRepository

class GetTaskListByNoteIdUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(noteId: Long): List<TaskByNoteDomainModel> {
        return noteRepository.getTaskListByNoteId(noteId)
    }
}