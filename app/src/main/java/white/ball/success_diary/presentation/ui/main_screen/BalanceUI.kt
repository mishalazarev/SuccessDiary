package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.main_screen.theme.LineCoffeeCoinBalanceColor
import white.ball.success_diary.presentation.ui.main_screen.theme.TextBalanceColor

@Composable
fun BalanceUI(
    balance: Int,
) {
    Box(
        modifier = Modifier
            .size(140.dp, 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(138.dp, 30.dp)
                .background(LineCoffeeCoinBalanceColor, RoundedCornerShape(35.dp)),
        )

        Row (
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(R.drawable.decor_coffee_coin),
                contentDescription = null
            )

            Text(
                text = balance.toString(),
                style = TextStyle(
                    color = TextBalanceColor,
                    fontSize = 24.sp
                )
            )

            Image(
                painter = painterResource(R.drawable.decor_add_coffee_coin),
                contentDescription = null
            )
        }
    }
}