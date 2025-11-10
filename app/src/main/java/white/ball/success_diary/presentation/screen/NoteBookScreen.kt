package white.ball.success_diary.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import white.ball.domain.extension_model.navigation.ScreenNavigation
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.note_book.TopBarNoteBookUI
import white.ball.success_diary.presentation.ui.note_book.swipe.SwipeContainer
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import white.ball.success_diary.presentation.view_model.NoteBookViewModel

@Composable
fun NoteBookScreen(
    noteBookViewModel: NoteBookViewModel,
    navController: NavController,
) {

    val noteListFiltered by noteBookViewModel.noteListFiltered.collectAsState(emptyList())

    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            Image(
                painter = painterResource(R.drawable.icon_add_action_bar),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        scope.launch {
                            noteBookViewModel.apply {
                                setTitle("")
                                setContent("")
                            }
                            noteBookViewModel.syncNewNote(null)
                        }
                        navController.navigate(ScreenNavigation.CREATE_NOTE_SCREEN.route)
                    }
                    .padding(bottom = 100.dp)
                    .clip(CircleShape),
            )
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackgroundColor)
                .padding(innerPadding)
                .padding(horizontal = 15.dp)
        ) {
            TopBarNoteBookUI(
                noteBookViewModel = noteBookViewModel
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(top = 7.dp)
            ) {

                items(
                    items = noteListFiltered,
                    key = { note -> note.noteId }
                ) { currentNote ->
                    SwipeContainer(
                        note = currentNote,
                        noteBookViewModel = noteBookViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}