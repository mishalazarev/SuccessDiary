package white.ball.success_diary.presentation.ui.note_book.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import white.ball.success_diary.presentation.ui.theme.BottomBarColor
import white.ball.success_diary.presentation.ui.theme.CardDefaultColor
import white.ball.success_diary.presentation.ui.theme.PageBlueColor
import white.ball.success_diary.presentation.ui.theme.PageGreenColor
import white.ball.success_diary.presentation.ui.theme.PagePinkColor
import white.ball.success_diary.presentation.ui.theme.PagePurpleColor
import white.ball.success_diary.presentation.ui.theme.PageRedColor
import white.ball.success_diary.presentation.ui.theme.PageWhiteColor
import white.ball.success_diary.presentation.ui.theme.PageYellowColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun DialogChangeColorUI(
    noteBookViewModel: NoteBookViewModel,
) {

    val colors = listOf(
        PageWhiteColor,
        PagePinkColor,
        PagePurpleColor,
        PageBlueColor,
        PageYellowColor,
        PageGreenColor,
        PageRedColor
    )

    Dialog(onDismissRequest = {
        noteBookViewModel.setDialogVisibleChangeColor(false)
    }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardDefaultColor
            )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BottomBarColor)
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Выберите цвет",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 24.sp
                        )
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(colors.size) { index ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(CircleShape)
                                .background(colors[index])
                                .clickable {
                                    noteBookViewModel.setColor(colors[index])
                                    noteBookViewModel.setDialogVisibleChangeColor(false)
                                }
                        )
                    }
                }
            }
        }
    }
}