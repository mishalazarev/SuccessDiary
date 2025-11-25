package white.ball.success_diary

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDateTime
import white.ball.success_diary.platform.app.service.TimerWorker
import white.ball.success_diary.presentation.navigation.MainNavController
import white.ball.success_diary.presentation.view_model.MainViewModel
import white.ball.success_diary.presentation.view_model.NoteBookViewModel
import white.ball.success_diary.presentation.ui.theme.SuccessDiaryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SuccessDiaryTheme {
                val navController = rememberNavController()

                val mainViewModel: MainViewModel = hiltViewModel()
                val noteBookViewModel: NoteBookViewModel = hiltViewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MainNavController(
                        navController = navController,
                        mainViewModel = mainViewModel,
                        noteBookViewModel = noteBookViewModel,
                        innerPadding = innerPadding,
                    ) {
                        this.finish()
                    }
                }
            }

        }



        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                TimerWorker.NOTIFICATION_ID
            )
        }

    }
}