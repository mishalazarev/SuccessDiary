package white.ball.success_diary.presentation.util.mapper

import white.ball.domain.model.NoteDomainModel
import white.ball.success_diary.presentation.model.NoteModelUI
import white.ball.success_diary.presentation.util.toColorUI


fun NoteDomainModel.toNoteModelUI(): NoteModelUI = NoteModelUI(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    createdDate = this.createdDate,
    color = this.color.toColorUI(),
    location = this.location,
)
