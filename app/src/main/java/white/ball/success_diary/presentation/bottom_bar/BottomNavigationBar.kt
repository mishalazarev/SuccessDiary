package white.ball.success_diary.presentation.bottom_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import white.ball.domain.extension_model.bottom_bar.BottomBar
import white.ball.success_diary.presentation.ui.theme.BottomBarColor
import white.ball.success_diary.presentation.ui.theme.BottomBarItemClickedColor
import white.ball.success_diary.presentation.view_model.MainViewModel

@Composable
fun BottomNavigationBar(
    mainViewModel: MainViewModel,
    navController: NavController,
) {
    val selectedNavigationIndex by mainViewModel.selectedNavigationBottomBarIndex.collectAsState(0)
    val isOpenTimer by mainViewModel.isTimerRunning.collectAsState(false)

    val bottomBarArray = BottomBar.entries.toTypedArray()

    AnimatedVisibility(
        visible = !isOpenTimer,
        enter = slideInVertically(initialOffsetY = {fullHeight -> fullHeight })
                + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight })
                + fadeOut()
    ) {
        NavigationBar(
            containerColor = BottomBarColor,
        ) {
            bottomBarArray.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedNavigationIndex == index,
                    onClick = {
                        mainViewModel.setSelectedNavigationBottomBarIndex(index)
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconDefault),
                            contentDescription = null
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BottomBarItemClickedColor,
                        indicatorColor = Color.Transparent,
                    ),
                )
            }
        }
    }
}