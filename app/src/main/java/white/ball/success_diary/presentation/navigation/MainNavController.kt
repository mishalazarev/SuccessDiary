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
import white.ball.success_diary.presentation.screen.AboutUsScreen
import white.ball.success_diary.presentation.screen.additional.CreateNoteScreen
import white.ball.success_diary.presentation.screen.additional.StoreScreen
import white.ball.success_diary.presentation.view_model.MainViewModel
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun MainNavController(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    noteBookViewModel: NoteBookViewModel,
    innerPadding: PaddingValues,
    finishApp: () -> Unit
) {

    val graph = navController.createGraph(startDestination = ScreenNavigation.MAIN_SCREEN.route) {

        composable(
            route = ScreenNavigation.MAIN_SCREEN.route
        ) {
            MainScreen(
                mainViewModel = mainViewModel,
                navController = navController,
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

        // Additional
        composable (
            route = ScreenNavigation.CREATE_NOTE_SCREEN.route
        ) {
            CreateNoteScreen(
                noteBookViewModel = noteBookViewModel,
                navController = navController,
                innerPadding = innerPadding,
            )
        }

        composable (
            route = ScreenNavigation.STORE_SCREEN.route
        ) {
            StoreScreen(
                mainViewModel = mainViewModel,
                navController = navController,
            )
        }

        composable (
            route = ScreenNavigation.ABOUT_US_SCREEN.route
        ) {
            AboutUsScreen(
                versionApp = "1.0.0"
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
        NavHost(
            navController = navController,
            graph = graph,
        )
    }
}