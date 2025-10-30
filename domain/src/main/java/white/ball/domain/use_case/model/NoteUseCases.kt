package white.ball.domain.use_case.model

import white.ball.domain.use_case.note.CreateNoteUseCase
import white.ball.domain.use_case.note.DeleteNoteUseCase
import white.ball.domain.use_case.note.EditNoteUseCase
import white.ball.domain.use_case.note.GetNoteListUseCase
import javax.inject.Inject

class NoteUseCases @Inject constructor(
    val getNoteListUseCase: GetNoteListUseCase,
    val addNoteUseCase: CreateNoteUseCase,
    val editNoteUseCase: EditNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)