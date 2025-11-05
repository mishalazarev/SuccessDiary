package white.ball.success_diary.presentation.model_ui

import androidx.compose.ui.graphics.Color
import white.ball.domain.extension_model.NoteLocation

data class ButtonLocationItemListModel(
    val textTitle: String,
    val iconImageResId: Int,
    val colorClicked: Color,
    var location: NoteLocation,
)
