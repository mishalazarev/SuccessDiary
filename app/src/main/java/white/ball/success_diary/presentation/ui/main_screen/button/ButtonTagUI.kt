package white.ball.success_diary.presentation.ui.main_screen.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun ButtonTagUI(
    mainViewModel: MainViewModel,
    openTagCollection: @Composable () -> Unit
) {

    val isOpenTagCollection by mainViewModel.isOpenDialogTagCollection.collectAsState(false)

    if (isOpenTagCollection) {
        openTagCollection()
    }

    TextButton(
        onClick = {
            mainViewModel.setDialogTagCollection(true)
        },
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(R.drawable.icon_tag_collection),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 9.dp)
                .rotate(-90f)
                .clip(CircleShape)
        )
    }
}