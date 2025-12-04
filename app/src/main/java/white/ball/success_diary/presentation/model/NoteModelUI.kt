package white.ball.success_diary.presentation.model

import androidx.compose.ui.graphics.Color
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.success_diary.presentation.ui.theme.PageWhiteColor
import white.ball.success_diary.presentation.util.toColorUI
import white.ball.success_diary.presentation.util.toDomainColor

data class NoteModelUI(
    val noteId: Long = 0,
    var title: String = "",
    var content: String = "",
    val createdDate: String,
    var color: Color = PageWhiteColor,
    var location: ItemLocation = ItemLocation.MAIN,
    var taskList: List<TaskByNoteDomainModel> = emptyList()
)

fun NoteModelUI.toNoteDomainModel(): NoteDomainModel = NoteDomainModel(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    createdDate = this.createdDate,
    color = this.color.toDomainColor(),
    location = this.location,
    taskList = this.taskList
)

fun NoteDomainModel.toNoteModelUI(): NoteModelUI = NoteModelUI(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    createdDate = this.createdDate,
    color = this.color.toColorUI(),
    location = this.location,
)