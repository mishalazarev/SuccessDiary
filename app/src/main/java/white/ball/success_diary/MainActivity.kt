package white.ball.success_diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import white.ball.success_diary.presentation.navigation.MainNavController
import white.ball.success_diary.presentation.view_model.MainViewModel
import white.ball.success_diary.presentation.view_model.NoteViewModel
import white.ball.success_diary.presentation.view_model.ProfileViewModel
import white.ball.success_diary.presentation.view_model.TaskViewModel
import white.ball.success_diary.presentation.ui.main_screen.theme.SuccessDiaryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuccessDiaryTheme {
                val navController = rememberNavController()

                val mainViewModel: MainViewModel = hiltViewModel()
                val noteViewModel: NoteViewModel = hiltViewModel()
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val taskViewModel: TaskViewModel = hiltViewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val targetValue = innerPadding

                    MainNavController(
                        navController = navController,
                        mainViewModel = mainViewModel,
                        noteViewModel = noteViewModel,
                        profileViewModel = profileViewModel,
                        taskViewModel = taskViewModel
                    ) {
                        this.finish()
                    }
                }
            }
        }
    }
}