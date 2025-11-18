package white.ball.success_diary.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.success_diary.presentation.ui.theme.ProgressBarBackgroundColor
import white.ball.success_diary.presentation.ui.theme.ProgressBarTrackLinearColor

@Composable
fun LinearProgressBarWithPercentUI(
    progress: Float
) {

    val height: Dp = 24.dp

    val percent = (progress.coerceIn(0f, 1f) * 100).toInt()

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp, vertical = 9.dp),
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
        ) {
            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height),
                color = ProgressBarTrackLinearColor,
                trackColor = ProgressBarBackgroundColor,
                strokeCap = StrokeCap.Square,
                gapSize = (-15).dp,
            )
        }

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "$percent %",
                style = TextStyle (
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        }
    }
}