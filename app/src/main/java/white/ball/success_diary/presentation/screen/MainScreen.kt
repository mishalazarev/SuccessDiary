package white.ball.success_diary.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import white.ball.success_diary.R
import white.ball.success_diary.platform.app.service.TimerWorker
import white.ball.success_diary.presentation.ui.main_screen.button.TimerButtonUI
import white.ball.success_diary.presentation.ui.main_screen.TopAppBarMainUI
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel
import java.util.UUID

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {

    Scaffold { innerPadding ->

        val isTimerRunning by mainViewModel.isTimerRunning.collectAsState(false)

        val minutesTimeLeft by remember { mutableIntStateOf(16) }

        val context = LocalContext.current

        val workManager = WorkManager.getInstance(context)

        val (currentWorkId, setCurrentWorkId) = remember { mutableStateOf<UUID?>(null) }

        val workInfo = currentWorkId?.let { id ->
            workManager.getWorkInfoByIdLiveData(id).observeAsState().value
        }

        val mainSecondsLeft = workInfo?.progress?.getInt(TimerWorker.MAIN_TIME_LEFT_KEY, -1) ?: -1

        val timeText = if (mainSecondsLeft >= 0) {
            val min = mainSecondsLeft / 60
            val sec = mainSecondsLeft % 60
            String.format("%02d:%02d", min, sec)
        } else "$minutesTimeLeft:00"

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
                    mainViewModel = mainViewModel
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                TimerButtonUI(
                    isTimerRunning
                ) {
                    val inputMainData = Data.Builder()
                        .putInt(TimerWorker.MAIN_KEY_MINUTES, minutesTimeLeft)
                        .build()

                    val workRequest = OneTimeWorkRequestBuilder<TimerWorker>()
                        .setInputData(inputMainData)
                        .build()

                    workManager.enqueue(workRequest)

                    mainViewModel.setTimer()

                    if (isTimerRunning) {
                        currentWorkId?.let { id ->
                            workManager.cancelWorkById(id)
                            setCurrentWorkId(null)
                        }
                    } else {
                        setCurrentWorkId(workRequest.id)
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
                )
            }
        }
    }
}