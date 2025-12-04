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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.main_screen.model.store.StoreCardMusicUI
import white.ball.success_diary.presentation.ui.main_screen.model.store.StoreCardTagUI
import white.ball.success_diary.presentation.ui.theme.CardDefaultColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.screen.main.MainViewModel

@Composable
fun StoreScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
) {

    val musicList by mainViewModel.musicList.collectAsState(emptyList())
    val selectedMusicPlay by mainViewModel.selectedShortPlayMusic.collectAsState(0)

    val tagList by mainViewModel.tagList.collectAsState(emptyList())

    Scaffold { innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .background(CardDefaultColor)
                .padding(innerPadding)
                .padding(horizontal = 6.dp)
                .padding(bottom = 80.dp)

        ) {

            item(span = { GridItemSpan(3) }) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MainBackgroundColor, RoundedCornerShape(5.dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Магазин",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .height(370.dp)
                        .fillMaxWidth()
                        .padding(top = 70.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    items(musicList.size) { index ->
                        val currentMusic = musicList[index]

                        StoreCardMusicUI(
                            mainViewModel = mainViewModel,
                            music = currentMusic,
                            isPlayMusic = selectedMusicPlay == currentMusic.rawResId,
                            index = index,
                        )
                    }
                }
            }

            items(tagList.size) { index ->
                val currentTag = tagList[index]
                StoreCardTagUI(
                    tag = currentTag,
                    mainViewModel = mainViewModel,
                    index = index,
                )
            }
        }
    }


    BackHandler {
        mainViewModel.stopLongMusic()
        navController.navigate(ScreenNavigation.MAIN_SCREEN.route) {
            popUpTo(ScreenNavigation.MAIN_SCREEN.route) {
                inclusive = true
            }
        }
    }
}