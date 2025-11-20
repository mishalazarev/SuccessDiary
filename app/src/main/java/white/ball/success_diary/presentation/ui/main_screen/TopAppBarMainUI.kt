package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import white.ball.success_diary.presentation.ui.main_screen.button.BalanceUI
import white.ball.success_diary.presentation.ui.main_screen.button.ButtonMusicUI
import white.ball.success_diary.presentation.ui.main_screen.button.ButtonTagUI
import white.ball.success_diary.presentation.ui.main_screen.dialog.DialogMusicCollectionUI
import white.ball.success_diary.presentation.ui.main_screen.dialog.DialogAddBalanceUI
import white.ball.success_diary.presentation.ui.main_screen.dialog.DialogTagCollectionDialogUI
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun TopAppBarMainUI(
    mainViewModel: MainViewModel,
    navController: NavController,
) {

    val isTimerRunning by mainViewModel.isTimerRunning.collectAsState(false)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ButtonMusicUI(
            mainViewModel = mainViewModel
        ) {
            DialogMusicCollectionUI(
                mainViewModel = mainViewModel
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
                DialogAddBalanceUI(
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
                BottomSheetMenuUI(
                    navController = navController,
                )
            }

            AnimatedVisibility(
                visible = !isTimerRunning,
                enter = fadeIn(tween(durationMillis = 300, easing = LinearEasing)),
                exit = fadeOut(tween(durationMillis = 300, easing = LinearEasing)),
            ) {
                ButtonTagUI(
                    mainViewModel = mainViewModel
                ) {
                    DialogTagCollectionDialogUI(
                        mainViewModel = mainViewModel
                    )
                }
            }
        }

    }
}