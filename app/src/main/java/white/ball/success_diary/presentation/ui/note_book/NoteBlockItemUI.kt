package white.ball.success_diary.presentation.ui.note_book

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.packInts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import white.ball.domain.extension_model.NoteLocation
import white.ball.success_diary.R
import white.ball.success_diary.presentation.model.NoteModelUI
import white.ball.success_diary.presentation.ui.theme.BottomBarItemDefaultColor
import white.ball.success_diary.presentation.ui.theme.ClickedButtonTimerColor
import white.ball.success_diary.presentation.ui.theme.DefaultButtonTimerColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun NoteItemUI(
    note: NoteModelUI,
    noteBookViewModel: NoteBookViewModel
) {
    val scope = rememberCoroutineScope()

    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {value ->
            when (value) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    scope.launch(Dispatchers.IO) {
                        noteBookViewModel.throwInTrashNote(note)
                    }
                    true
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    scope.launch(Dispatchers.IO) {
                        noteBookViewModel.throwInMain(note)
                    }
                    true
                }

                else -> true
            }
        },
    )

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        backgroundContent = {
            when (swipeToDismissBoxState.dismissDirection) {

                SwipeToDismissBoxValue.StartToEnd -> {
                    Image(
                        painter = painterResource(R.drawable.decor_trash),
                        contentDescription = "Trash",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 9.dp)
                            .height(70.dp)
                            .background(ClickedButtonTimerColor)
                    )
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 9.dp)
                            .height(70.dp)
                            .background(BottomBarItemDefaultColor)
                    )
                }

                else -> {  }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp)
                .background(note.color, RoundedCornerShape(10.dp))
                .animateContentSize(),
        ) {
            Text(
                text = note.title,
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                ),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = note.createdDate,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ),
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 10.dp)
                )
            }
        }
    }
}