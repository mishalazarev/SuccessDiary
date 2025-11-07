package white.ball.success_diary.presentation.view_model

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import white.ball.domain.extension_model.NoteLocation
import white.ball.domain.use_case.model.NoteUseCases
import white.ball.success_diary.presentation.model.NoteModelUI
import white.ball.success_diary.presentation.model_ui.ButtonLocationItemListModel
import white.ball.success_diary.presentation.util.mapper.toNote
import white.ball.success_diary.presentation.util.mapper.toNoteModelUI
import javax.inject.Inject

@HiltViewModel
class NoteBookViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<NoteModelUI>>(emptyList())
    val noteList: Flow<List<NoteModelUI>> = _noteList

    private val _clickedNote = MutableStateFlow<NoteModelUI?>(null)
    val clickedNote: Flow<NoteModelUI?> = _clickedNote

    private val _isSelectedButton = MutableStateFlow<ButtonLocationItemListModel?>(null)
    val isSelectedButton: Flow<ButtonLocationItemListModel?> = _isSelectedButton

    val locationListener = MutableStateFlow(NoteLocation.MAIN)


    init {
        loadNoteListFromLocalStorage()
    }

    fun loadClickedNote(note: NoteModelUI) {
        _clickedNote.value = note

        setTitle(note.title)
        setContent(note.content)
    }

    fun loadNoteListFromLocalStorage() {
        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.getNoteListUseCase().collect {
                _noteList.value = it.map { it.toNoteModelUI() }
            }
        }
    }

    fun reloadNoteListFlow(note: NoteModelUI) {
        _noteList.value.toMutableList().add(note)

        _noteList.value = _noteList.value.filter {
            it.noteId != note.noteId
        }
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
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
        val correctedFormatDate = currentDate.format(formatter)

        val note = NoteModelUI(
            title = _clickedNote.value?.title ?: "",
            content = _clickedNote.value?.content ?: "",
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

    suspend fun editeNote() {
        _clickedNote.value?.let { clickedNoteInFlow ->
            _noteList.value.find { it.noteId == clickedNoteInFlow.noteId }?.let {
                noteUseCases.editNoteUseCase(clickedNoteInFlow.toNote())
                reloadNoteListFlow(clickedNoteInFlow)
            }
        }
        _clickedNote.value = null
    }

    suspend fun editeNote(note: NoteModelUI) {
        _noteList.value.find { it.noteId == note.noteId }?.let {
            noteUseCases.editNoteUseCase(note.toNote())
        } ?: noteUseCases.addNoteUseCase(note.toNote())
        reloadNoteListFlow(note)
    }

    fun changeNoteColor(color: Color) {
        _clickedNote.value = _clickedNote.value?.copy(color = color)
    }

    fun throwInMain(note: NoteModelUI) {
        viewModelScope.launch(Dispatchers.IO) {
            note.location = NoteLocation.MAIN
            editeNote(note)

            reloadNoteListFlow(note = note)
        }
    }

    fun throwInTrashNote(note: NoteModelUI) {
        viewModelScope.launch(Dispatchers.IO) {
            note.location = NoteLocation.DELETED
            editeNote(note)

            reloadNoteListFlow(note = note)
        }
    }

    suspend fun deleteNote(note: NoteModelUI) {
        noteUseCases.deleteNoteUseCase(note.toNote())
    }

    suspend fun deleteNote() {
        _clickedNote.value?.let {
            noteUseCases.deleteNoteUseCase(it.toNote())
        }
    }

    fun setTitle(text: String) {
        _clickedNote.value = _clickedNote.value?.copy(title = text)
    }
    fun setContent(text: String) {
        _clickedNote.value = _clickedNote.value?.copy(content = text)
    }

}