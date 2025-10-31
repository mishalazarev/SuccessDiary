package white.ball.success_diary.presentation.bottom_bar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import white.ball.domain.extension_model.bottom_bar.BottomBar
import white.ball.success_diary.presentation.ui.main_screen.theme.BottomBarColor
import white.ball.success_diary.presentation.ui.main_screen.theme.BottomBarItemClickedColor

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }

    val bottomBarArray = BottomBar.entries.toTypedArray()

    NavigationBar (
        containerColor = BottomBarColor
    ) {
        bottomBarArray.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconDefault),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors (
                    selectedIconColor = BottomBarItemClickedColor,
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }

}