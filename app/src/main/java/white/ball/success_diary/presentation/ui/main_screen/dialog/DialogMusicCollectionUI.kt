package white.ball.success_diary.presentation.ui.main_screen.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.main_screen.model.CardMusicUI
import white.ball.success_diary.presentation.ui.theme.CardDefaultColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun DialogMusicCollectionUI(
    mainViewModel: MainViewModel
) {

    val musicCollection by mainViewModel.musicList.collectAsState(emptyList())

    val selectedMusicPlay by mainViewModel.selectedPlayMusic.collectAsState(0)

    Dialog(
        onDismissRequest = {
            mainViewModel.setDialogMusicStore(false)
            mainViewModel.stopMusic()
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardDefaultColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MainBackgroundColor)
                        .height(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Музыка",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    )
                }

                LazyRow (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    items(musicCollection.size) { index ->
                        val currentMusic = musicCollection[index]

                        CardMusicUI(
                            mainViewModel = mainViewModel,
                            music = currentMusic,
                            isPlayMusic = selectedMusicPlay == currentMusic.rawResId,
                        )

                    }
                }
            }
        }
    }
}