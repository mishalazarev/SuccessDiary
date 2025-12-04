package white.ball.success_diary.presentation.ui.main_screen.model.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import white.ball.domain.collection.TagCollection
import white.ball.domain.model.TagDomainModel
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.screen.main.MainViewModel

@Composable
fun PlayerCardTagUI(
    tagDomainModel: TagDomainModel,
    isSelected: Boolean,
    onTagSelected: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(70.dp)
            .padding(start = 10.dp, top = 9.dp)
            .clickable {
                onTagSelected()
            },
        colors = CardDefaults.cardColors(
            containerColor = (
                    if (isSelected)
                        MainBackgroundColor
                    else
                        Color.White
                    )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = tagDomainModel.imageResId,
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
            )
        }
    }
}