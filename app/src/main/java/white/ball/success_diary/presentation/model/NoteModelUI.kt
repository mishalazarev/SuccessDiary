package white.ball.success_diary.presentation.model

import androidx.compose.ui.graphics.Color
import white.ball.domain.extension_model.NoteLocation
import white.ball.success_diary.presentation.ui.theme.NotePinkColor

data class NoteModelUI(
    val noteId: Long = 0,
    var title: String,
    var content: String,
    val createdDate: String = "",
    var color: Color = NotePinkColor,
    var location: NoteLocation,
    val userOwnerId: Long = 0,
)
