package white.ball.success_diary.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import white.ball.domain.extension_model.NoteLocation
import white.ball.domain.use_case.model.NoteUseCases
import white.ball.success_diary.presentation.model.NoteModelUI
import white.ball.success_diary.presentation.util.mapper.toNote
import white.ball.success_diary.presentation.util.mapper.toNoteModelUI
import javax.inject.Inject

@HiltViewModel
class NoteBookViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<NoteModelUI>>(emptyList())
    val noteList: Flow<List<NoteModelUI>> = _noteList

    val title = MutableStateFlow("")
    val content = MutableStateFlow("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.getNoteListUseCase().collect {
                _noteList.value = it.map { it.toNoteModelUI() }
            }
        }
    }

    fun setTitle(text: String) {
        title.value = text
    }

    fun setContent(text: String) {
        content.value = text
    }

    suspend fun addNote() {
        val note = NoteModelUI(
            title = title.value,
            content = content.value,
            location = NoteLocation.MAIN,
        )

        noteUseCases.addNoteUseCase(note.toNote())
    }

    suspend fun editeNote(note: NoteModelUI) {
        noteUseCases.editNoteUseCase(note.toNote())
    }

    suspend fun throwInTrashNote(note: NoteModelUI) {

    }

    suspend fun throwInArchiveNote(note: NoteModelUI) {

    }

    suspend fun deleteNote(note: NoteModelUI) {
        noteUseCases.deleteNoteUseCase(note.toNote())
    }



}