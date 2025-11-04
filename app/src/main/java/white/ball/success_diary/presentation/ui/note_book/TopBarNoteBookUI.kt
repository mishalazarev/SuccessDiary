package white.ball.success_diary.presentation.ui.note_book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.LineCoffeeCoinBalanceColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun TopBarNoteBookUI(
    noteBookViewModel: NoteBookViewModel,
) {
    var textField by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = textField,
            onValueChange = {
                textField = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedBorderColor = LineCoffeeCoinBalanceColor,
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
                            }
                    )
                }
            },
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row (
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(10.dp)
                    .fillMaxWidth(0.45f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.decor_archive),
                    contentDescription = null,
                )

                Text(
                    text = "Архив",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ),
                    modifier = Modifier
                        .padding(start = 9.dp)
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            Row (
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.decor_trash),
                    contentDescription = null,
                )

                Text(
                    text = "Корзина",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ),
                    modifier = Modifier
                        .padding(start = 9.dp)
                )
            }
        }
    }
}