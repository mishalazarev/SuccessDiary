package white.ball.success_diary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import white.ball.domain.extension_model.ScreenNavigation
import white.ball.success_diary.presentation.screen.MainScreen

@Composable
fun MainNavController(
    navController: NavHostController,
//    mainViewModel: MainViewModel,
//    noteViewModel: NoteViewModel,
//    profileViewModel: ProfileViewModel,
//    taskViewModel: TaskViewModel,
    finishApp: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.MAIN_SCREEN.route
    ) {
        composable(
            route = ScreenNavigation.MAIN_SCREEN.route
        ) {
            MainScreen()
        }
    }
}