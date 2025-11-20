package white.ball.success_diary.presentation.ui.main_screen.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.domain.model.Music
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.DefaultButtonTimerColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor

@Composable
fun CardMusicUI (
    music: Music
) {

    Column(
        modifier = Modifier
            .height(70.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(music.imageResId),
                contentDescription = music.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = music.title,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainBackgroundColor,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ),
                )

                Text(
                    text = music.title,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }

            Button (
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = DefaultButtonTimerColor
                )
            ) {
                Text(
                    text = "Купить".uppercase()
                )
            }
        }
    }
}