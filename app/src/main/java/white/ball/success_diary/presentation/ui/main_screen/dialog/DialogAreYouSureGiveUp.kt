package white.ball.success_diary.presentation.ui.main_screen.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import white.ball.success_diary.presentation.ui.theme.CardDefaultColor
import white.ball.success_diary.presentation.ui.theme.DefaultButtonTimerColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun DialogAreYouSureGiveUp(
    mainViewModel: MainViewModel,
) {


    Dialog(
        onDismissRequest = {
            mainViewModel.setAction(MainViewModel.DIALOG_GIVE_UP,false)
        },
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardDefaultColor,
                    contentColor = Color.Black
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Неужели сдаешься??",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .padding(vertical = 40.dp, horizontal = 16.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,

                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                mainViewModel.setAction(MainViewModel.DIALOG_GIVE_UP,false)
                                mainViewModel.stopTimer()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(
                                text = "Сдаюсь",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                        }

                        Button(
                            onClick = {
                                mainViewModel.setAction(MainViewModel.DIALOG_GIVE_UP,false)
                                mainViewModel.startTimer()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DefaultButtonTimerColor,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(
                                text = "Продолжаю",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}