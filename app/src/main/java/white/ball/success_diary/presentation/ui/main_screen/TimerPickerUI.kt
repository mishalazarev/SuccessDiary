package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun TimerPickerUI(
    mainViewModel: MainViewModel,
) {

    val numbersListState = rememberLazyListState(
        initialFirstVisibleItemIndex = number
    )
}