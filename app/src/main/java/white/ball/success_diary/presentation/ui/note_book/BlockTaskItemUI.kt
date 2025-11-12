package white.ball.success_diary.presentation.ui.note_book

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.domain.model.additional.TaskDomainModel
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.BottomBarItemDefaultColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun BlockTaskItemUI (
    noteBookViewModel: NoteBookViewModel,
    task: TaskDomainModel,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = task.title,
            onValueChange = { text ->
                val updatedTask = task.copy(
                    title = text
                )

                noteBookViewModel.setTask(task = updatedTask)
            },
            modifier = Modifier
                .weight(0.75f),
            placeholder = {
                Text(
                    text = "Задача".uppercase(),
                    style = TextStyle (
                        color = BottomBarItemDefaultColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                textDecoration = if (task.isDone)
                    TextDecoration.LineThrough
                 else
                    TextDecoration.None
            )
        )

        Checkbox(
            checked = task.isDone,
            onCheckedChange = { turn ->
                val updatedTask = task.copy(
                    isDone = turn
                )
                noteBookViewModel.setTask(updatedTask)
            },
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(0.20f)
        )
    }
}