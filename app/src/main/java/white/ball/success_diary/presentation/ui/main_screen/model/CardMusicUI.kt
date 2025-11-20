package white.ball.success_diary.presentation.ui.main_screen.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import white.ball.domain.model.Music
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.DefaultButtonTimerColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun CardMusicUI(
    mainViewModel: MainViewModel,
    music: Music,
    isPlayMusic: Boolean,
) {

    Card(
        modifier = Modifier
            .height(300.dp)
            .padding(start = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
                    .clickable {
                        if (isPlayMusic) {
                            mainViewModel.stopMusic()
                        } else {
                            mainViewModel.setSelectedOnTeenSecondsMusic(music.rawResId)
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = music.imageResId,
                    contentDescription = music.title,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                )

                Image(
                    painter = painterResource(
                        if (isPlayMusic) {
                            R.drawable.icon_pause
                        } else {
                            R.drawable.icon_play
                        }
                    ),
                    contentDescription = null
                )

            }


            Text(
                text = music.title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainBackgroundColor,
                    fontFamily = FontFamily(Font(R.font.roboto))
                ),
                modifier = Modifier
                    .padding(top = 9.dp)
            )

            Text(
                text = music.artist,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.roboto))
                ),
            )


            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = DefaultButtonTimerColor
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = music.price.toString()
                    )

                    Image(
                        painter = painterResource(R.drawable.decor_coffee_coin),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(start = 9.dp)
                    )
                }
            }
        }
    }
}