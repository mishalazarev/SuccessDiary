package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import white.ball.success_diary.R
import white.ball.success_diary.ui.theme.ClickedButtonTimerColor
import white.ball.success_diary.ui.theme.DefaultButtonTimerColor
import white.ball.success_diary.ui.theme.MainBackgroundColor

@Preview(showBackground = true)
@Composable
fun ButtonTimerUI(
//    imageTagResId: Int,
//    backgroundColor: Int
) {
    val isStartedButton by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = if (isStartedButton) {
                ClickedButtonTimerColor
            } else {
                DefaultButtonTimerColor
            }, shape = CircleShape)
            .padding(10.dp)
            .background(
                color = MainBackgroundColor,
                shape = CircleShape
            )
            .padding(10.dp)
            .background(color = if (isStartedButton) {
                ClickedButtonTimerColor
            } else {
                DefaultButtonTimerColor
            }, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.tag_dumbbells),
            contentDescription = null
        )
    }
}