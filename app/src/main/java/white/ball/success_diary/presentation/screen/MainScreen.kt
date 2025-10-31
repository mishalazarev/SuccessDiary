package white.ball.success_diary.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import white.ball.success_diary.presentation.ui.main_screen.BalanceUI
import white.ball.success_diary.presentation.ui.main_screen.ButtonTimerUI
import white.ball.success_diary.presentation.ui.main_screen.theme.MainBackgroundColor
import java.util.UUID

@Composable
fun MainScreen() {
    Scaffold { innerPadding ->

        var isStartedButton by remember { mutableStateOf(false) }
        val minutesTimeLeft by remember { mutableIntStateOf(16) }
        val cancelSecondsTimeLeft by remember { mutableIntStateOf(10) }

        val context = LocalContext.current

        val workManager = WorkManager.getInstance(context)

        val (currentWorkId, setCurrentWorkId) = remember { mutableStateOf<UUID?>(null) }

        val workInfo = currentWorkId?.let { id ->
            workManager.getWorkInfoByIdLiveData(id).observeAsState().value
        }

        val mainSecondsLeft = workInfo?.progress?.getInt(TimerWorker.MAIN_TIME_LEFT_KEY, -1) ?: -1
        val secondsLeftForCancel = workInfo?.progress?.getInt(TimerWorker.TIME_FOR_CANCEL_KEY, 10) ?: 10

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
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TextButton(
                    onClick = {

                    },
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_menu),
                        contentDescription = null
                    )
                }

                BalanceUI(145)

                TextButton(
                    onClick = {

                    },
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_music_play),
                        contentDescription = null,
                    )
                }
            }

                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = "Осталось $secondsLeftForCancel секунд для отмены.",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        ),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )

                    ButtonTimerUI(
                        isStartedButton
                    ) {
                        val inputMainData = Data.Builder()
                            .putInt(TimerWorker.MAIN_KEY_MINUTES, minutesTimeLeft)
                            .putInt(TimerWorker.TIME_FOR_CANCEL_KEY, cancelSecondsTimeLeft)
                            .build()


                        val workRequest = OneTimeWorkRequestBuilder<TimerWorker>()
                            .setInputData(inputMainData)
                            .build()

                        workManager.enqueue(workRequest)

                        isStartedButton = !isStartedButton

                        if (isStartedButton) {
                            setCurrentWorkId(workRequest.id)
                        } else {
                            currentWorkId?.let { id ->
                                workManager.cancelWorkById(id)
                                setCurrentWorkId(null)
                            }
                        }

                    }

                    Text(
                        text = timeText,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 96.sp,
                            fontFamily = FontFamily(Font(R.font.post_no_bills_colombo))
                        )
                    )
                }

        }
    }
}