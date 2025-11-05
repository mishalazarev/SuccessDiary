package white.ball.success_diary.presentation.screen.additional

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.note_book.BottomSheetNoteMenuUI
import white.ball.success_diary.presentation.ui.theme.BottomBarItemDefaultColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun CreateNoteScreen(
    noteBookViewModel: NoteBookViewModel,
    navController: NavController,
    innerPadding: PaddingValues,
) {

    val title by noteBookViewModel.title.collectAsState()
    val content by noteBookViewModel.content.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(start = 10.dp, end = 10.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ScreenNavigation.NOTE_BOOK_SCREEN.route)
                        }
                )

                BottomSheetNoteMenuUI(
                    noteBookViewModel = noteBookViewModel,
                    navController = navController,
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackgroundColor)
                .padding(innerPadding)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { text ->
                    noteBookViewModel.setTitle(text)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Заголовок".uppercase(),
                        style = TextStyle(
                            color = BottomBarItemDefaultColor,
                            fontSize = 25.sp,
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
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                )
            )

            OutlinedTextField(
                value = content,
                onValueChange = { text ->
                    noteBookViewModel.setContent(text)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                placeholder = {
                    Text(
                        text = "Описание".uppercase(),
                        style = TextStyle(
                            color = BottomBarItemDefaultColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            )
        }
    }
}