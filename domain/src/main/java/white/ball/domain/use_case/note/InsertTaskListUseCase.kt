package white.ball.domain.use_case.note

import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.repository.NoteRepository
import javax.inject.Inject

class InsertTaskListUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(taskByNoteDomainModel: List<TaskByNoteDomainModel>) {
        noteRepository.insertTaskList(taskByNoteDomainModel)
    }
}