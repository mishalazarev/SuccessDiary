package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.LineCoffeeCoinBalanceColor
import white.ball.success_diary.presentation.ui.theme.TextBalanceColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun BalanceUI(
    mainViewModel: MainViewModel,
    openAddBalance: @Composable () -> Unit
) {

    val coffeeCoin by mainViewModel.coffeeCoin.collectAsState(null)

    val isOpenBalanceDialog by mainViewModel.isOpenDialogBalance.collectAsState(false)

    if (isOpenBalanceDialog) {
        openAddBalance()
    }

    Box(
        modifier = Modifier
            .size(140.dp, 40.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                mainViewModel.setDialogBalance()
            },
        contentAlignment = Alignment.Center,
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
                text = coffeeCoin?.balance.toString() ?: "0",
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