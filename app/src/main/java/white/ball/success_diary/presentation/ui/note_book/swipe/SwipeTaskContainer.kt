package white.ball.success_diary.presentation.ui.note_book.swipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.note_book.BlockTaskItemUI
import white.ball.success_diary.presentation.ui.theme.ClickedButtonTimerColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun SwipeTaskContainer(
    taskByNoteDomainModel: TaskByNoteDomainModel,
    noteBookViewModel: NoteBookViewModel,
) {
    var isRemoved by remember (taskByNoteDomainModel.localId) { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val swipeState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.StartToEnd) {
                isRemoved = true
            }
            false
        }
    )

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            shrinkTowards = Alignment.CenterVertically,
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = swipeState,
            backgroundContent = {
                when (swipeState.dismissDirection) {
                    SwipeToDismissBoxValue.StartToEnd -> {
                        Image(
                            painter = painterResource(R.drawable.decor_trash),
                            contentDescription = "Trash",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 9.dp)
                                .height(70.dp)
                                .background(
                                    ClickedButtonTimerColor
                                )
                        )
                        scope.launch {
                            swipeState.reset()
                        }
                    }

                    else -> {}
                }
            }
        ) {
            BlockTaskItemUI(
                noteBookViewModel = noteBookViewModel,
                task = taskByNoteDomainModel,
            )
        }

        LaunchedEffect(key1 = isRemoved) {
            if (isRemoved) {
                noteBookViewModel.deleteTask(taskByNoteDomainModel)
            }
        }
    }
}