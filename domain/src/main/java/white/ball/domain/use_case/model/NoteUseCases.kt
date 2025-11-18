package white.ball.domain.use_case.model

import white.ball.domain.use_case.note.CreateNoteUseCase
import white.ball.domain.use_case.note.DeleteNoteUseCase
import white.ball.domain.use_case.note.DeleteTaskUseCase
import white.ball.domain.use_case.note.EditNoteUseCase
import white.ball.domain.use_case.note.GetNoteByIdUseCase
import white.ball.domain.use_case.note.GetNoteListUseCase
import white.ball.domain.use_case.note.GetTaskListByNoteIdUseCase
import white.ball.domain.use_case.note.InsertTaskListUseCase
import white.ball.domain.use_case.note.InsertTaskUseCase
import javax.inject.Inject

class NoteUseCases @Inject constructor(
    val getNoteListUseCase: GetNoteListUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
    val getTaskListByNoteIdUseCase: GetTaskListByNoteIdUseCase,
    val addNoteUseCase: CreateNoteUseCase,
    val insertTaskUseCase: InsertTaskUseCase,
    val insertTaskListUseCase: InsertTaskListUseCase,
    val editNoteUseCase: EditNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
)