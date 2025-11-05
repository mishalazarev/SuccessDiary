package white.ball.success_diary.presentation.ui.note_book

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.model_ui.ButtonLocationItemListModel

@Composable
fun ButtonLocationItemListUI(
    buttonLocationItemListModel: ButtonLocationItemListModel,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Row (
        modifier = modifier
            .background(
                if (isSelected)
                buttonLocationItemListModel.colorClicked
            else
                Color.White,
                RoundedCornerShape(10.dp))
            .padding(10.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(buttonLocationItemListModel.iconImageResId),
            contentDescription = null,
            tint = if (isSelected)
                Color.White
            else
                buttonLocationItemListModel.colorClicked
        )

        Text(
            text = buttonLocationItemListModel.textTitle,
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.roboto))
            ),
            modifier = Modifier
                .padding(start = 9.dp)
        )
    }
}