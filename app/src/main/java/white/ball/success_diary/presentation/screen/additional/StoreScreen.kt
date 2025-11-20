package white.ball.success_diary.presentation.screen.additional

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.presentation.ui.main_screen.model.CardMusicUI
import white.ball.success_diary.presentation.ui.theme.CardDefaultColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun StoreScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
) {

    val musicCollection by mainViewModel.musicList.collectAsState(emptyList())

    val selectedMusicPlay by mainViewModel.selectedPlayMusic.collectAsState(0)

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(CardDefaultColor)
                .fillMaxSize()
        ) {
            LazyRow (
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
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

    BackHandler {
        mainViewModel.stopMusic()
        navController.navigate(ScreenNavigation.MAIN_SCREEN.route) {
            popUpTo(ScreenNavigation.MAIN_SCREEN.route) {
                inclusive = true
            }
        }
    }
}