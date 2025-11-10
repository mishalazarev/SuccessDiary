package white.ball.success_diary.presentation.ui.main_screen.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun TagCollectionDialogUI(
    mainViewModel: MainViewModel
) {

    Dialog(
        onDismissRequest = {

        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MainBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Теги",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    )
                }

//                LazyHorizontalGrid(
//                    rows = GridCells.Fixed(4),
//                ) {
//                    items()
//                }
            }
        }
    }
}