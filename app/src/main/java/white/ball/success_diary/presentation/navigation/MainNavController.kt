package white.ball.success_diary.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import white.ball.domain.extension_model.bottom_bar.BottomBar
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.presentation.bottom_bar.BottomNavigationBar
import white.ball.success_diary.presentation.screen.MainScreen
import white.ball.success_diary.presentation.screen.NoteBookScreen
import white.ball.success_diary.presentation.screen.ProfileScreen
import white.ball.success_diary.presentation.screen.TaskListScreen
import white.ball.success_diary.presentation.view_model.MainViewModel
import white.ball.success_diary.presentation.view_model.NoteViewModel
import white.ball.success_diary.presentation.view_model.ProfileViewModel
import white.ball.success_diary.presentation.view_model.TaskViewModel

@Composable
fun MainNavController(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    noteViewModel: NoteViewModel,
    profileViewModel: ProfileViewModel,
    taskViewModel: TaskViewModel,
    finishApp: () -> Unit
) {

    val graph = navController.createGraph(startDestination = ScreenNavigation.MAIN_SCREEN.route) {

        composable(
            route = ScreenNavigation.MAIN_SCREEN.route
        ) {
            MainScreen()
        }

        composable (
            route = ScreenNavigation.NOTE_BOOK_SCREEN.route
        ) {
            NoteBookScreen()
        }

        composable (
            route = ScreenNavigation.TASK_LIST_SCREEN.route
        ) {
            TaskListScreen()
        }

        composable (
            route = ScreenNavigation.PROFILE_SCREEN.route
        ) {
            ProfileScreen()
        }
    }

    Scaffold (
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        val stubInner = innerPadding

        NavHost(
            navController = navController,
            graph = graph,
        )
    }
}