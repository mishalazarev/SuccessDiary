package white.ball.success_diary.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.main_screen.BalanceUI
import white.ball.success_diary.ui.theme.MainBackgroundColor

@Composable
fun MainScreen() {
    Scaffold (
        bottomBar = {

        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackgroundColor)
                .padding(innerPadding)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_music_play),
                        contentDescription = null,
                    )

                    BalanceUI(145)

                    Image(
                        painter = painterResource(R.drawable.icon_menu),
                        contentDescription = null
                    )
                }
            }
        }

    }
}