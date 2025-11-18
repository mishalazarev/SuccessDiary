package white.ball.domain.use_case.note

import jakarta.inject.Inject
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.repository.NoteRepository

class DeleteTaskUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(taskByNoteDomainModel: TaskByNoteDomainModel) {
        noteRepository.deleteTask(taskByNoteDomainModel)
    }
}