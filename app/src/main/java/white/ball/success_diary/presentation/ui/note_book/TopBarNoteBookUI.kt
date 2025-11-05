package white.ball.success_diary.presentation.ui.note_book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import white.ball.domain.extension_model.NoteLocation
import white.ball.success_diary.R
import white.ball.success_diary.presentation.model_ui.ButtonLocationItemListModel
import white.ball.success_diary.presentation.ui.theme.BottomBarItemDefaultColor
import white.ball.success_diary.presentation.ui.theme.ClickedButtonTimerColor
import white.ball.success_diary.presentation.ui.theme.LineCoffeeCoinBalanceColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun TopBarNoteBookUI(
    noteBookViewModel: NoteBookViewModel,
) {
    val noteList by noteBookViewModel.noteList.collectAsState(emptyList())

    var textField by remember { mutableStateOf("") }

    val selectedButton by noteBookViewModel.isSelectedButton.collectAsState(null)

    val scope = rememberCoroutineScope()

    val buttonSortedList by remember {
        mutableStateOf(
            listOf(
                ButtonLocationItemListModel(
                    textTitle = "Корзина",
                    iconImageResId = R.drawable.decor_trash,
                    colorClicked = ClickedButtonTimerColor,
                    location = NoteLocation.DELETED
                ),
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        OutlinedTextField(
            value = textField,
            onValueChange = {
                textField = it

                if (it.isEmpty()) {
                    noteBookViewModel.loadNoteListFromLocalStorage()
                } else {
                    noteBookViewModel.searchNote(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedBorderColor = LineCoffeeCoinBalanceColor,
                focusedTextColor = Color.Black
            ),
            placeholder = {
                Text(
                    text = "Поиск",
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                )
            },
            trailingIcon = {
                if (textField.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable {
                                textField = ""
                                noteBookViewModel.loadNoteListFromLocalStorage()
                            }
                    )
                }
            },
        )

        Row(
            modifier = Modifier
                .padding(top = 9.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            buttonSortedList.forEach { currentButton ->
                ButtonLocationItemListUI(
                    buttonLocationItemListModel = currentButton,
                    onClick = { noteBookViewModel.setSelectedButton(currentButton) },
                    isSelected = currentButton == selectedButton,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }

        if (selectedButton != null) {
            Button(
                onClick = {
                    noteList.filter { it.location == NoteLocation.DELETED }.forEach {
                        scope.launch(Dispatchers.IO) {
                            noteBookViewModel.deleteNote(it)
                        }
                    }
                    selectedButton?.let {
                        noteBookViewModel.setSelectedButton(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 9.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Очистить",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}
