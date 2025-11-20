package white.ball.success_diary.presentation.ui.main_screen.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import white.ball.success_diary.presentation.ui.main_screen.model.CardTagUI
import white.ball.success_diary.presentation.ui.theme.CardDefaultColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun DialogTagCollectionDialogUI(
    mainViewModel: MainViewModel
) {

    val availableTag by mainViewModel.availableTag.collectAsState(emptyList())

    val isSelectedTag by mainViewModel.selectedTag.collectAsState(null)

    Dialog(
        onDismissRequest = {
            mainViewModel.setDialogTagCollection(false)
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardDefaultColor
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
                        text = "Режим таймера",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    )
                }

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(220.dp)
                        .padding(9.dp)
                ) {
                    items(availableTag.size) { index ->
                        val currentTag = availableTag[index]
                        CardTagUI(
                            tag = currentTag,
                            mainViewModel = mainViewModel,
                            isSelected = isSelectedTag == currentTag
                        )
                    }
                }
            }
        }
    }
}