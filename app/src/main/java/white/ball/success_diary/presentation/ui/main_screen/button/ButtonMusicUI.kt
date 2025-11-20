package white.ball.success_diary.presentation.ui.main_screen.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import white.ball.success_diary.R
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun ButtonMusicUI(
    mainViewModel: MainViewModel,
    openMusicDialog: @Composable () -> Unit
) {

    val isOpenDialogMusic by mainViewModel.isOpenDialogMusicCollection.collectAsState(false)
    val isTimerRunning by mainViewModel.isTimerRunning.collectAsState(false)

    if (isOpenDialogMusic) {
        openMusicDialog()
    }

    TextButton(
        onClick = {
            if (isOpenDialogMusic && !isTimerRunning) {

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
}