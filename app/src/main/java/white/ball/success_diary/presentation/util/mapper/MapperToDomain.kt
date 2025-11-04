package white.ball.success_diary.presentation.util.mapper

import white.ball.domain.model.NoteDomainModel
import white.ball.success_diary.presentation.model.NoteModelUI
import white.ball.success_diary.presentation.util.toDomainColor

fun NoteModelUI.toNote(): NoteDomainModel = NoteDomainModel(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    creationDate = this.creationDate,
    color = this.color.toDomainColor(),
    location = this.location,
    userOwnerId = this.userOwnerId
)