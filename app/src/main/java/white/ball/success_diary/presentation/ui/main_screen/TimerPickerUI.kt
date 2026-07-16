package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import white.ball.domain.extension_model.TimerTime
import white.ball.success_diary.presentation.custom_view.TimerTimePicker
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.screen.main.MainViewModel

@Composable
fun TimerPickerUI(
    mainViewModel: MainViewModel,
) {
    val numberList = TimerTime.entries.map { it.time }

    val infiniteNumberList = List(3000) { index ->
        numberList[index % numberList.size]
    }

    val selectedTime by mainViewModel.selectedTime.collectAsState(20)

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = infiniteNumberList.size / 2
    )

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val layoutInfo = listState.layoutInfo
            val center = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            val closest = layoutInfo.visibleItemsInfo.minByOrNull { item ->
                kotlin.math.abs((item.offset + item.size / 2) - center)
            }

            closest?.let {
                mainViewModel.setSelectedTime(infiniteNumberList[it.index])
            }
        }
    }

    AndroidView(
        factory = { context ->
            TimerTimePicker(context).apply {
                onTimeChanged = { time: Int ->
                    mainViewModel.setSelectedTime(time)
                }
            }
        },
        update = { view ->
            view.setTime(selectedTime)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 9.dp, start = 20.dp, end = 30.dp),
    )

    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedTime.toString(),
            color = MainBackgroundColor,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}