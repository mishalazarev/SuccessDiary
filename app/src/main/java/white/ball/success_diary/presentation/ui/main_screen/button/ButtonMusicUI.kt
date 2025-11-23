package white.ball.success_diary.presentation.ui.main_screen.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import white.ball.success_diary.R
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun ButtonMusicUI(
    mainViewModel: MainViewModel,
) {
    var imageResId by remember { mutableIntStateOf( R.drawable.icon_music_default) }

    TextButton(
        onClick = {
            if (mainViewModel.isPlayingMusic()) {
                mainViewModel.stopLongMusic()
                imageResId = R.drawable.icon_music_default
            } else {
                mainViewModel.playLongMusic()
                imageResId = R.drawable.icon_music_clicked
            }
        },
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = null,
        )
    }
}