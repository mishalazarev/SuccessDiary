package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.LineCoffeeCoinBalanceColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun ShowDialogAddBalanceUI(
    mainViewModel: MainViewModel
) {

    val coffeeCoin by mainViewModel.coffeeCoin.collectAsState(null)

    val isOpenDialogBalance by mainViewModel.isOpenDialogBalance.collectAsState(false)
    val gradientForBackgroundImage = listOf(Color.Yellow, MainBackgroundColor, LineCoffeeCoinBalanceColor)

    val scope = rememberCoroutineScope()

    if (isOpenDialogBalance) {
        Dialog(
            onDismissRequest = {
                mainViewModel.setDialogBalance()
            },
            content = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.branch_coffee_tree),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Кофейный магазин",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                )
                            )
                        }

                        Text(
                            text = "Утрой свой фокус при помощи кофе.",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Black,
                            ),
                            modifier = Modifier
                                .padding(top = 9.dp, start = 16.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(70.dp)
                                    .border(
                                        3.dp,
                                        Brush.linearGradient(gradientForBackgroundImage),
                                        RoundedCornerShape(10.dp)
                                    )
                                    .background(
                                        Color.White, RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.decor_coffee_cup),
                                    contentDescription = null
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 9.dp)
                            ) {
                                Text(
                                    text = "Чашечка кофе",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Black,
                                    ),
                                    maxLines = 2,
                                    modifier = Modifier
                                        .width(70.dp),
                                )

                                Text(
                                    text = "24 часа",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = LineCoffeeCoinBalanceColor,
                                    ),
                                    modifier = Modifier
                                )
                            }

                            Button(
                                onClick = {
                                    scope.launch (Dispatchers.IO) {
                                        coffeeCoin?.let {
                                            mainViewModel.updateBalance(it.balance + 45)
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = LineCoffeeCoinBalanceColor,
                                    contentColor = Color.White
                                ),
                            ) {
                                Text(
                                    text = "45.00 ₽",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 9.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(70.dp)
                                    .border(
                                        3.dp,
                                        Brush.linearGradient(gradientForBackgroundImage),
                                        RoundedCornerShape(10.dp)
                                    )
                                    .background(
                                        Color.White,
                                        RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.decor_thermos),
                                    contentDescription = null
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 9.dp)
                            ) {
                                Text(
                                    text = "Термос с кофе",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Black,
                                    ),
                                    maxLines = 2,
                                    modifier = Modifier
                                        .width(70.dp),
                                )

                                Text(
                                    text = "3 суток",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        color = LineCoffeeCoinBalanceColor
                                    ),
                                    modifier = Modifier
                                )
                            }

                            Button(
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = LineCoffeeCoinBalanceColor,
                                    contentColor = Color.White
                                ),
                            ) {
                                Text(
                                    text = "120.00 ₽",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}