package white.ball.success_diary.presentation.ui.main_screen.button

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.ClickedButtonTimerColor
import white.ball.success_diary.presentation.ui.theme.DefaultButtonTimerColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun TimerButtonUI(
    mainViewModel: MainViewModel,
    isTimerRunning: Boolean,
    onClick: () -> Unit
) {

    val selectedTag by mainViewModel.selectedTag.collectAsState(null)

    val buttonSizeAnimation by animateDpAsState(
        targetValue = if (isTimerRunning) {
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
        targetValue = if (isTimerRunning) {
            200.dp
        } else {
            120.dp
        },
        animationSpec = tween (
            durationMillis = 400,
            easing = EaseInBack
        )
    )

    Box(
        modifier = Modifier
            .size(buttonSizeAnimation)
            .background(color = if (isTimerRunning) {
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
            .background(color = if (isTimerRunning) {
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
        AsyncImage(
            model = selectedTag?.imageResId ?: R.drawable.tag_dumbbells,
            contentDescription = null,
            modifier = Modifier
                .size(tagSizeAnimation)
        )
    }
}