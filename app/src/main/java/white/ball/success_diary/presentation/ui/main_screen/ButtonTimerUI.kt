package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.main_screen.theme.ClickedButtonTimerColor
import white.ball.success_diary.presentation.ui.main_screen.theme.DefaultButtonTimerColor
import white.ball.success_diary.presentation.ui.main_screen.theme.MainBackgroundColor

@Composable
fun ButtonTimerUI(
//    backgroundColor: Int,
    isStartedButton: Boolean,
    onClick: () -> Unit
) {

    val buttonSizeAnimation by animateDpAsState(
        targetValue = if (isStartedButton) {
            300.dp
        } else {
            200.dp
        },
        animationSpec = tween (
            durationMillis = 400,
            easing = EaseInBack
        )
    )

    val tagSizeAnimation by animateDpAsState(
        targetValue = if (isStartedButton) {
            220.dp
        } else {
            140.dp
        },
        animationSpec = tween (
            durationMillis = 400,
            easing = EaseInBack
        )
    )

    Box(
        modifier = Modifier
            .size(buttonSizeAnimation)
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
            }, shape = CircleShape)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.tag_dumbbells),
            contentDescription = null,
            modifier = Modifier
                .size(tagSizeAnimation)
        )
    }
}