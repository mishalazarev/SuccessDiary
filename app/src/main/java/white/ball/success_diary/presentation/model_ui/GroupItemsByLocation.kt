package white.ball.success_diary.presentation.model_ui

import androidx.compose.ui.graphics.Color
import white.ball.domain.extension_model.ItemLocation

data class GroupItemsByLocation(
    val textTitle: String,
    val iconImageResId: Int,
    val colorClicked: Color,
    var location: ItemLocation,
)
