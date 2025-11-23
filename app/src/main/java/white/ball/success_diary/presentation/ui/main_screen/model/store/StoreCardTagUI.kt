package white.ball.success_diary.presentation.ui.main_screen.model.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import white.ball.domain.model.Tag
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.BottomBarItemDefaultColor
import white.ball.success_diary.presentation.ui.theme.DefaultButtonTimerColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun StoreCardTagUI(
    tag: Tag,
    mainViewModel: MainViewModel,
    index: Int,
) {

    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .size(height = 150.dp, width = 120.dp)
            .padding(
                top = 9.dp,
                start = if (index == 0 || index % 3 == 0)
                    0.dp
                else
                    9.dp
            )
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = tag.imageResId,
                contentDescription = tag.title,
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 9.dp)
            )

            Button(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        mainViewModel.buyTag(tag)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = if (tag.price == 0) BottomBarItemDefaultColor else DefaultButtonTimerColor
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = if (tag.price == 0) "Доступно" else tag.price.toString()
                    )

                    if (tag.price != 0) {
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
}