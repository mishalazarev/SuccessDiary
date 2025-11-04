package white.ball.success_diary.presentation.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.BottomBarColor
import white.ball.success_diary.presentation.ui.theme.LineCoffeeCoinBalanceColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetMenuUI() {
    val bottomSheetState = rememberModalBottomSheetState()
    var isOpenBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetTextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily(Font(R.font.roboto))
    )

    TextButton(
        onClick = {
            isOpenBottomSheet = true
        },
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(R.drawable.icon_main_menu),
            contentDescription = null,
        )
    }

    if (isOpenBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                isOpenBottomSheet = false
            },
            sheetState = bottomSheetState,
            containerColor = BottomBarColor
        ) {
            Text(
                text = "Тэги",
                modifier = Modifier
                    .padding(16.dp),
                style = bottomSheetTextStyle
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                color = LineCoffeeCoinBalanceColor
            )

            Text(
                text = "История",
                modifier = Modifier
                    .padding(16.dp),
                style = bottomSheetTextStyle
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                color = LineCoffeeCoinBalanceColor)

            Text(
                text = "Задания",
                modifier = Modifier
                    .padding(16.dp),
                style = bottomSheetTextStyle
            )

            HorizontalDivider(
                modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
                color = LineCoffeeCoinBalanceColor)

            Text(
                text = "Новости",
                modifier = Modifier
                    .padding(16.dp),
                style = bottomSheetTextStyle
            )
        }
    }
}