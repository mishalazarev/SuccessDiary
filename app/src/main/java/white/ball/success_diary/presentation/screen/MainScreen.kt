package white.ball.success_diary.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.main_screen.button.TimerButtonUI
import white.ball.success_diary.presentation.ui.main_screen.TopAppBarMainUI
import white.ball.success_diary.presentation.ui.main_screen.dialog.DialogPlayerCollectionUI
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
) {

    Scaffold { innerPadding ->

        val isTimerRunning by mainViewModel.isStartTimer.collectAsState(false)
        val timerTime by mainViewModel.selectedTime.collectAsState(45)
        val secondsLeft by mainViewModel.timeLeft.collectAsState(-1)

        val isOpenDialogCustomizeTimer by mainViewModel.isOpenDialogCustomizeTimerCollection.collectAsState(
            false
        )

        val scope = rememberCoroutineScope()

        val timeText = if (secondsLeft >= 0) {
            val min = secondsLeft / 60
            val sec = secondsLeft % 60
            String.format("%02d:%02d", min, sec)
        } else "$timerTime:00"



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackgroundColor)
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                TopAppBarMainUI(
                    mainViewModel = mainViewModel,
                    navController = navController,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TimerButtonUI(
                    mainViewModel = mainViewModel,
                    isTimerRunning = isTimerRunning
                ) {
                    if (isTimerRunning) {
                        scope.launch {
                            mainViewModel.stopTimer()
                        }
                    } else {
                        mainViewModel.startTimer()
                    }
                }

                Text(
                    text = timeText,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 96.sp,
                        fontFamily = FontFamily(Font(R.font.post_no_bills_colombo))
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable {
                            if (!isTimerRunning) {
                                mainViewModel.setDialogCustomizeTimer(true)
                            }
                        }
                )

                if (isOpenDialogCustomizeTimer) {
                    DialogPlayerCollectionUI(
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}