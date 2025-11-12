package white.ball.success_diary.presentation.model

import androidx.compose.ui.graphics.Color
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.model.additional.TaskDomainModel
import white.ball.success_diary.presentation.ui.theme.PageWhiteColor

data class NoteModelUI(
    val noteId: Long = 0,
    var title: String = "",
    var content: String = "",
    val createdDate: String,
    var color: Color = PageWhiteColor,
    var location: ItemLocation = ItemLocation.MAIN,
    var taskList: List<TaskDomainModel> = emptyList()
)
