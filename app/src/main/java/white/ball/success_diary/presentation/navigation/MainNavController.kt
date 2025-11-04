package white.ball.success_diary.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.presentation.bottom_bar.BottomNavigationBar
import white.ball.success_diary.presentation.screen.MainScreen
import white.ball.success_diary.presentation.screen.NoteBookScreen
import white.ball.success_diary.presentation.screen.ProfileScreen
import white.ball.success_diary.presentation.screen.TaskListScreen
import white.ball.success_diary.presentation.screen.additional.CreateNoteScreen
import white.ball.success_diary.presentation.view_model.MainViewModel
import white.ball.success_diary.presentation.view_model.NoteBookViewModel
import white.ball.success_diary.presentation.view_model.ProfileViewModel
import white.ball.success_diary.presentation.view_model.TaskListViewModel

@Composable
fun MainNavController(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    noteBookViewModel: NoteBookViewModel,
    profileViewModel: ProfileViewModel,
    taskListViewModel: TaskListViewModel,
    innerPadding: PaddingValues,
    finishApp: () -> Unit
) {

    val graph = navController.createGraph(startDestination = ScreenNavigation.MAIN_SCREEN.route) {

        composable(
            route = ScreenNavigation.MAIN_SCREEN.route
        ) {
            MainScreen(
                mainViewModel = mainViewModel
            )
        }

        composable (
            route = ScreenNavigation.NOTE_BOOK_SCREEN.route
        ) {
            NoteBookScreen(
                noteBookViewModel = noteBookViewModel,
                navController = navController,
            )
        }

        composable (
            route = ScreenNavigation.TASK_LIST_SCREEN.route
        ) {
            TaskListScreen(
                taskListViewModel = taskListViewModel
            )
        }

        composable (
            route = ScreenNavigation.PROFILE_SCREEN.route
        ) {
            ProfileScreen(
                profileViewModel = profileViewModel
            )
        }

        composable (
            route = ScreenNavigation.CREATE_SCREEN.route
        ) {
            CreateNoteScreen(
                noteBookViewModel = noteBookViewModel,
                navController = navController,
                innerPadding = innerPadding,
            )
        }
    }

    Scaffold (
        bottomBar = {
            BottomNavigationBar(
                mainViewModel = mainViewModel,
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