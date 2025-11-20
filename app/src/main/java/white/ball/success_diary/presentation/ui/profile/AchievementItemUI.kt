package white.ball.success_diary.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import white.ball.domain.extension_model.AchievementMeasurement
import white.ball.domain.model.Achievement
import white.ball.domain.model.additional.AchievementTask
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.AchievementBlockColor

@Composable
fun AchievementItemUI(
    achievement: Achievement
) {

    Column(
        modifier = Modifier
            .padding(top = 9.dp, start = 5.dp, end = 5.dp)
            .fillMaxWidth()
            .background(AchievementBlockColor, RoundedCornerShape(10.dp))
            .padding(top = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
                Icon(
                    painter = painterResource(achievement.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.White
                )

                Text(
                    text = achievement.title,
                    modifier = Modifier
                        .padding(start = 35.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    )
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            items(achievement.achievementTaskList.size) {
                Text(
                    text = achievement.achievementTaskList[it].title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                )

                Checkbox(
                    checked = achievement.achievementTaskList[it].isCompleted,
                    onCheckedChange = {},
                    modifier = Modifier
                        .padding(end = 9.dp)
                )
            }
        }

        LinearProgressBarWithPercentUI(
            progress = calculateProgress(
                currentNumber = achievement.current,
                taskList = achievement.achievementTaskList,
            )
        )
    }
}

fun calculateProgress(
    taskList: List<AchievementTask>,
    currentNumber: Int,
): Float {
    if (currentNumber == 0) return 0f

    var maxNumber = taskList
        .filter { !it.isCompleted }
        .map { it.title }
        .first()
        .toFloat()

    return currentNumber.toFloat() / maxNumber
}