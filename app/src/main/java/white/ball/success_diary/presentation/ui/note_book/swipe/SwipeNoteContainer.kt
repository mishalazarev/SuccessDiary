package white.ball.success_diary.presentation.ui.note_book.swipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.domain.extension_model.swipe.DirectionSwipe
import white.ball.success_diary.R
import white.ball.success_diary.presentation.model.NoteModelUI
import white.ball.success_diary.presentation.ui.note_book.NoteItemUI
import white.ball.success_diary.presentation.ui.theme.BottomBarItemDefaultColor
import white.ball.success_diary.presentation.ui.theme.ClickedButtonTimerColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun SwipeNoteContainer(
    note: NoteModelUI,
    noteBookViewModel: NoteBookViewModel,
    navController: NavController,
) {
    val locationListener by noteBookViewModel.locationListener.collectAsState()
    var isRemoved by remember(note.noteId) { mutableStateOf(false) }
    var swipeDirection by remember(note.noteId) { mutableStateOf(DirectionSwipe.NONE) }

    val scope = rememberCoroutineScope()

    val swipeState = rememberSwipeToDismissBoxState(
        confirmValueChange = { targetValue ->
            if (targetValue == SwipeToDismissBoxValue.StartToEnd) {
                swipeDirection = DirectionSwipe.RIGHT
                isRemoved = true
                return@rememberSwipeToDismissBoxState true
            }
            if (targetValue == SwipeToDismissBoxValue.EndToStart) {
                if (locationListener == ItemLocation.DELETED) {
                    swipeDirection = DirectionSwipe.LEFT
                    isRemoved = true
                    return@rememberSwipeToDismissBoxState true
                } else {
                    return@rememberSwipeToDismissBoxState false
                }
            }
            false
        }
    )

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            shrinkTowards = Alignment.CenterVertically
        ) + fadeOut(
        )
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
                                    ClickedButtonTimerColor,
                                    RoundedCornerShape(10.dp)
                                )
                        )
                        scope.launch {
                            swipeState.reset()
                        }
                    }

                    SwipeToDismissBoxValue.EndToStart -> {
                        if (note.location == ItemLocation.DELETED) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 9.dp)
                                    .height(70.dp)
                                    .background(
                                        BottomBarItemDefaultColor,
                                        RoundedCornerShape(10.dp)
                                    )
                            )
                            scope.launch {
                                swipeState.reset()
                            }
                        }
                    }

                    else -> {}
                }
            }
        ) {
            NoteItemUI(
                note = note,
            ) {
                noteBookViewModel.syncNewNote(note)
                navController.navigate(ScreenNavigation.CREATE_NOTE_SCREEN.route)
            }
        }
    }

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            when (locationListener) {
                ItemLocation.MAIN -> {
                    if (swipeDirection == DirectionSwipe.RIGHT) {
                        noteBookViewModel.throwInTrashNote(note)
                    }
                }

                ItemLocation.DELETED -> {
                    if (swipeDirection == DirectionSwipe.RIGHT) {
                        noteBookViewModel.deleteNote(note)
                    } else if (swipeDirection == DirectionSwipe.LEFT) {
                        noteBookViewModel.throwInMain(note)
                    }
                }
            }
        }
    }
}