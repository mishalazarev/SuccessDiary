package white.ball.domain.use_case.note

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<List<NoteDomainModel>> {
        return noteRepository.getNoteList()
     }
}