package white.ball.success_diary.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.note_book.NoteItemUI
import white.ball.success_diary.presentation.ui.note_book.TopBarNoteBookUI
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun NoteBookScreen(
    noteBookViewModel: NoteBookViewModel,
    navController: NavController,
) {

    val noteList by noteBookViewModel.noteList.collectAsState(emptyList())

    Scaffold (
        floatingActionButton = {
            Image (
                painter = painterResource(R.drawable.icon_add_action_bar),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        navController.navigate(ScreenNavigation.CREATE_SCREEN.route)
                    }
                    .padding(bottom = 100.dp)
                    .clip(CircleShape),
            )
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackgroundColor)
                .padding(innerPadding)
                .padding(horizontal = 15.dp)
        ) {
            TopBarNoteBookUI(
                noteBookViewModel = noteBookViewModel
            )

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                items(noteList.size) {
                    NoteItemUI(
                        note = noteList[it]
                    )
                }
             }
        }
    }
}