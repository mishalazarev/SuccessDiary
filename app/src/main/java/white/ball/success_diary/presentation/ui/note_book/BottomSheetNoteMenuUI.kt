package white.ball.success_diary.presentation.ui.note_book

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import white.ball.success_diary.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.presentation.ui.theme.BottomBarColor
import white.ball.success_diary.presentation.ui.theme.LineCoffeeCoinBalanceColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetNoteMenuUI(
    noteBookViewModel: NoteBookViewModel,
    navController: NavController,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var isOpenBottomSheet by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val bottomSheetTextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily(Font(R.font.roboto)),
        color = Color.White
    )

    val textBottomModifier = Modifier
        .fillMaxWidth()
        .padding(top = 9.dp)
        .height(50.dp)

    Image(
        painter = painterResource(R.drawable.icon_menu),
        contentDescription = null,
        modifier = Modifier
            .clickable {
                isOpenBottomSheet = true
            }
    )

    if (isOpenBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                isOpenBottomSheet = false
            },
            sheetState = bottomSheetState,
            containerColor = BottomBarColor,
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                TextButton(
                    onClick = {
                        val addJob = scope.async (Dispatchers.IO) {
                            noteBookViewModel.addNote()
                        }

                        navController.navigate(ScreenNavigation.NOTE_BOOK_SCREEN.route) {
                            popUpTo (0) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }

                       scope.async (Dispatchers.Main) {
                            addJob.await()
                            noteBookViewModel.setContent("")
                            noteBookViewModel.setTitle("")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LineCoffeeCoinBalanceColor
                    ),
                    modifier = textBottomModifier,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Сохранить",
                        style = bottomSheetTextStyle
                    )
                }

                TextButton(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LineCoffeeCoinBalanceColor
                    ),
                    modifier = textBottomModifier,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Сменить цвет",
                        style = bottomSheetTextStyle
                    )
                }

                TextButton(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LineCoffeeCoinBalanceColor
                    ),
                    modifier = textBottomModifier,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Удалить",
                        style = bottomSheetTextStyle
                    )
                }
            }
        }
    }

}