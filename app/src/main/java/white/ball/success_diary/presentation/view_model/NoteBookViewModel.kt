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
import white.ball.success_diary.presentation.model_ui.ButtonLocationItemListModel
import white.ball.success_diary.presentation.util.mapper.toNote
import white.ball.success_diary.presentation.util.mapper.toNoteModelUI
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class NoteBookViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<NoteModelUI>>(emptyList())
    val noteList: Flow<List<NoteModelUI>> = _noteList

    private val _isSelectedButton = MutableStateFlow<ButtonLocationItemListModel?>(null)
    val isSelectedButton: Flow<ButtonLocationItemListModel?> = _isSelectedButton

    val locationListener = MutableStateFlow(NoteLocation.MAIN)

    val title = MutableStateFlow("")
    val content = MutableStateFlow("")

    init {
        loadNoteListFromLocalStorage()
    }

    fun loadNoteListFromLocalStorage() {
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

    fun setSelectedButton(button: ButtonLocationItemListModel) {
        if (button == _isSelectedButton.value) {
            _isSelectedButton.value = null
            locationListener.value = NoteLocation.MAIN
        } else {
            _isSelectedButton.value = button
            locationListener.value = button.location
        }
    }

    suspend fun addNote() {
        val currentDate = LocalDateTime.now()
        val correctedFormatDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))

        val note = NoteModelUI(
            title = title.value,
            content = content.value,
            location = NoteLocation.MAIN,
            createdDate = correctedFormatDate
        )

        noteUseCases.addNoteUseCase(note.toNote())
    }

    fun searchNote(title: String) {
        val query = title.trim().lowercase()
        _noteList.value = _noteList.value.filter { note ->
            note.title.lowercase().contains(query)
        }
    }

    suspend fun editeNote(note: NoteModelUI) {
        noteUseCases.editNoteUseCase(note.toNote())
    }

    suspend fun throwInMain(note: NoteModelUI) {
        note.location = NoteLocation.MAIN
        editeNote(note)
    }

    suspend fun throwInTrashNote(note: NoteModelUI) {
        note.location = NoteLocation.DELETED
        editeNote(note)
    }

    suspend fun deleteNote(note: NoteModelUI) {
        noteUseCases.deleteNoteUseCase(note.toNote())
    }



}