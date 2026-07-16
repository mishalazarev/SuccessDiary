package white.ball.success_diary.presentation.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import white.ball.domain.extension_model.DatePeriod
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor

@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel
) {

    val currentDateScheduleState by scheduleViewModel.currentDateSchedule.collectAsStateWithLifecycle()
    val titleDatePeriod by scheduleViewModel.titleDatePeriod.collectAsStateWithLifecycle()

        Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .background(MainBackgroundColor)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 14.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                    .border(1.dp, Color.White,RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
                        .background(if (currentDateScheduleState == DatePeriod.DAY) Color.White else Color.Transparent, RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
                        .clickable{
                            scheduleViewModel.setCurrentSchedule(DatePeriod.DAY)
                        }
                ) {
                    Text(
                        text = "День",
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 20.dp),
                        color = if (currentDateScheduleState == DatePeriod.DAY) MainBackgroundColor else Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.White,RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
                        .background(if (currentDateScheduleState == DatePeriod.WEEK) Color.White else Color.Transparent, RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
                        .clickable{
                            scheduleViewModel.setCurrentSchedule(DatePeriod.WEEK)
                        },
                ) {
                    Text(
                        text = "Неделя",
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 20.dp),
                        color = if (currentDateScheduleState == DatePeriod.WEEK) MainBackgroundColor else Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.White,RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp))
                        .background(if (currentDateScheduleState == DatePeriod.ALL_TIME) Color.White else Color.Transparent, RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp))
                        .clickable{
                            scheduleViewModel.setCurrentSchedule(DatePeriod.ALL_TIME)
                        },
                ) {
                    Text(
                        text = "За всё время",
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 20.dp),
                        color = if (currentDateScheduleState == DatePeriod.ALL_TIME) MainBackgroundColor else Color.White
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardDoubleArrowLeft,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Text(
                    text = titleDatePeriod,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraLight
                    )
                )

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardDoubleArrowRight,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}