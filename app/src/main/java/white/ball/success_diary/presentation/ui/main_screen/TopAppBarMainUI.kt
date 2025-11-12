package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.main_screen.button.BalanceUI
import white.ball.success_diary.presentation.ui.main_screen.dialog.DialogMusicCollectionUI
import white.ball.success_diary.presentation.ui.main_screen.dialog.ShowDialogAddBalanceUI
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun TopAppBarMainUI(
    mainViewModel: MainViewModel
) {

    val isOpenDialogMusicCollection by mainViewModel.isOpenDialogTagCollection.collectAsState(false)

    val isTimerRunning by mainViewModel.isTimerRunning.collectAsState(false)

    if (isOpenDialogMusicCollection) {
        DialogMusicCollectionUI(
            mainViewModel = mainViewModel
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TextButton(
            onClick = {
                if (isTimerRunning) {

                } else {
                    mainViewModel.setDialogMusicStore(true)
                }
            },
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(
                    if (isTimerRunning)
                        R.drawable.icon_music_clicked
                    else
                        R.drawable.icon_music_default
                ),
                contentDescription = null,
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

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            AnimatedVisibility(
                visible = !isTimerRunning,
                enter = fadeIn(tween(durationMillis = 300, easing = LinearEasing)),
                exit = fadeOut(tween(durationMillis = 300, easing = LinearEasing)),
            ) {
                BottomSheetMenuUI()
            }


            AnimatedVisibility(
                visible = !isTimerRunning,
                enter = fadeIn(tween(durationMillis = 300, easing = LinearEasing)),
                exit = fadeOut(tween(durationMillis = 300, easing = LinearEasing)),
            ) {
                TextButton(
                    onClick = {

                    },
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_tag_collection),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 9.dp)
                            .rotate(-90f)
                            .clip(CircleShape)
                    )
                }
            }
        }

    }
}