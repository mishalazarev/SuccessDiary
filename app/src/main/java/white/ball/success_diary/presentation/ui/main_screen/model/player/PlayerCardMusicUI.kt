package white.ball.success_diary.presentation.ui.main_screen.model.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import white.ball.domain.model.MusicDomainModel
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor

@Composable
fun PlayerCardMusicUI(
    musicDomainModel: MusicDomainModel,
    isSelected: Boolean,
    onMusicSelected: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(top = 9.dp)
            .size(120.dp, height = 105.dp)
            .padding(start = 15.dp)
            .background( if (isSelected)
                MainBackgroundColor
            else
                Color.White,
                RoundedCornerShape(10.dp))
            .clickable {
                onMusicSelected()
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 60.dp),
            colors = CardDefaults.cardColors(
                containerColor = (
                        if (isSelected)
                            MainBackgroundColor
                        else
                            Color.White
                        )
            ),
            shape = RoundedCornerShape(bottomEnd = 0.dp, bottomStart = 0.dp, topEnd = 10.dp, topStart = 10.dp)
        ) {
            AsyncImage(
                model = musicDomainModel.imageResId,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            text = musicDomainModel.title,
            modifier = Modifier
                .padding(top = 6.dp, start = 2.dp, end = 2.dp),
            style = TextStyle(
                color = if (isSelected)
                    Color.White
                else
                    MainBackgroundColor,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            softWrap = true,
            textAlign = TextAlign.Center,
        )
    }
}