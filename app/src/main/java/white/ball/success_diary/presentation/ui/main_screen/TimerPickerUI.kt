package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.domain.extension_model.TimerTime
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun TimerPickerUI(
    mainViewModel: MainViewModel,
) {
    val numberList = TimerTime.entries.map { it.time }

    val infiniteNumberList = List(3000) { index ->
        numberList[index % numberList.size]
    }

    val selectedTime by mainViewModel.selectedTime.collectAsState(45)

    val numbersListState = rememberLazyListState(
        initialFirstVisibleItemIndex = infiniteNumberList.size / 2
    )

    LaunchedEffect(numbersListState.isScrollInProgress) {
        if (!numbersListState.isScrollInProgress) {
            val centerIndex = (numbersListState.firstVisibleItemIndex + 2)
                .coerceIn(0, infiniteNumberList.size - 1)

            val realValue = infiniteNumberList[centerIndex]
            mainViewModel.setSelectedTime(realValue)
        }
    }

    LaunchedEffect(selectedTime) {
        val index = infiniteNumberList.indexOfFirst { it == selectedTime }
        if (index != -1) {

            val safeIndex = (index - 2).coerceIn(0, infiniteNumberList.size - 1)

            numbersListState.animateScrollToItem(safeIndex)
        }
    }

    LazyRow(
        state = numbersListState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 9.dp)
        ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(infiniteNumberList.size) { index ->
            val currentNumber = infiniteNumberList[index]
            val isSelected = selectedTime == currentNumber

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(horizontal = 6.dp, vertical = 9.dp)
                    .background(Color.White, RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currentNumber.toString(),
                    style = TextStyle(
                        color = if (isSelected) MainBackgroundColor else Color.DarkGray,
                        fontSize = if (isSelected) 26.sp else 16.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    ),
                )
            }
        }
    }
}