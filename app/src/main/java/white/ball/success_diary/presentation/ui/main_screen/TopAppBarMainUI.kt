package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.BottomBarItemClickedColor
import white.ball.success_diary.presentation.ui.theme.BottomBarItemDefaultColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun TopAppBarMainUI(
    mainViewModel: MainViewModel
) {
    val isTimerRunning by mainViewModel.isTimerRunning.collectAsState(false)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = {

            },
            shape = CircleShape
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_music_play),
                contentDescription = null,
                tint = if (isTimerRunning) {
                    BottomBarItemClickedColor
                } else {
                    Color.Gray
                }
            )

        }

        AnimatedVisibility(
            visible = !isTimerRunning,
            enter = fadeIn(tween(durationMillis = 300, easing = LinearEasing)),
            exit = fadeOut(tween(durationMillis = 300, easing = LinearEasing)),
        ) {
            BalanceUI(
                mainViewModel = mainViewModel
            ) {
                ShowDialogAddBalanceUI(
                    mainViewModel = mainViewModel
                )
            }

        }

        AnimatedVisibility(
            visible = !isTimerRunning,
            enter = fadeIn(tween(durationMillis = 300, easing = LinearEasing)),
            exit = fadeOut(tween(durationMillis = 300, easing = LinearEasing)),
        ) {
            BottomSheetMenuUI()
        }
    }
}