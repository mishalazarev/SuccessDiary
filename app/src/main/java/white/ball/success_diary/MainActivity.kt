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
import white.ball.success_diary.presentation.view_model.NoteBookViewModel
import white.ball.success_diary.presentation.view_model.ProfileViewModel
import white.ball.success_diary.presentation.ui.theme.SuccessDiaryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuccessDiaryTheme {
                val navController = rememberNavController()

                val mainViewModel: MainViewModel = hiltViewModel()
                val noteBookViewModel: NoteBookViewModel = hiltViewModel()
                val profileViewModel: ProfileViewModel = hiltViewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainNavController(
                        navController = navController,
                        mainViewModel = mainViewModel,
                        noteBookViewModel = noteBookViewModel,
                        profileViewModel = profileViewModel,
                        innerPadding = innerPadding,
                    ) {
                        this.finish()
                    }
                }
            }
        }
    }
}