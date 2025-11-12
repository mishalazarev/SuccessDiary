package white.ball.success_diary.presentation.view_model

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.swipe.DirectionSwipe
import white.ball.domain.model.additional.TaskDomainModel
import white.ball.domain.use_case.model.NoteUseCases
import white.ball.success_diary.presentation.model.NoteModelUI
import white.ball.success_diary.presentation.model_ui.GroupItemsByLocation
import white.ball.success_diary.presentation.util.mapper.toNote
import white.ball.success_diary.presentation.util.mapper.toNoteModelUI
import javax.inject.Inject
import kotlin.math.log



@HiltViewModel
class NoteBookViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<NoteModelUI>>(emptyList())

    private val _clickedNote = MutableStateFlow<NoteModelUI?>(null)
    val clickedNote: Flow<NoteModelUI?> = _clickedNote

    private val _isSelectedFilteredButton = MutableStateFlow<GroupItemsByLocation?>(null)
    val isSelectedFilteredButton: Flow<GroupItemsByLocation?> = _isSelectedFilteredButton

    private val _isOpenDialogChangeColor = MutableStateFlow(false)
    val isOpenDialogChangeColor: Flow<Boolean> = _isOpenDialogChangeColor

    val locationListener = MutableStateFlow(ItemLocation.MAIN)

    private val _noteListFiltered = MutableStateFlow<List<NoteModelUI>>(emptyList())
    val noteListFiltered: Flow<List<NoteModelUI>> = _noteListFiltered


    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadFromLocalStorage()
        }

        viewModelScope.launch {
            combine(_noteList, locationListener) { list, location ->
                list.filter { it.location == location }
            }.collect { filtered ->
                _noteListFiltered.value = filtered
            }
        }
    }

    suspend fun loadFromLocalStorage() {
        noteUseCases.getNoteListUseCase().collect { list ->
            _noteList.value = list.map { it.toNoteModelUI() }
        }
    }

    fun manualFiltered(note: NoteModelUI) {
        _noteList.value = _noteList.value.filterNot { it.noteId == note.noteId }
    }

    fun syncNewNote(note: NoteModelUI?) {
        var currentNote = note
        if (currentNote == null) {
            val dateTimeNow = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
            val formatted = dateTimeNow.format(formatter)

            currentNote = NoteModelUI(
                createdDate = formatted
            )
        }

        _clickedNote.value = currentNote
    }

    suspend fun addNote(note: NoteModelUI) {
        val noteDomain = note.toNote()
        val noteId = noteUseCases.addNoteUseCase(noteDomain)

        _clickedNote.value = _clickedNote.value?.copy(
            noteId = noteId,
            taskList = _clickedNote.value?.taskList?.map { it.copy(noteId = noteId) } ?: emptyList()
        )
    }


    suspend fun editNote(note: NoteModelUI) {
        noteUseCases.editNoteUseCase(note.toNote())
    }

    suspend fun deleteNote(note: NoteModelUI) {
        noteUseCases.deleteNoteUseCase(note.toNote())
    }

    suspend fun throwInTrashNote(note: NoteModelUI) {
        note.location = ItemLocation.DELETED

        _noteList.value = _noteList.value.map {
            if (it.noteId == note.noteId) note else it
        }

        manualFiltered(note)

        editNote(note)
    }

    suspend fun throwInMain(note: NoteModelUI) {
        note.location = ItemLocation.MAIN

        _noteList.value = _noteList.value.map {
            if (it.noteId == note.noteId) note else it
        }

        manualFiltered(note)

        editNote(note)
    }

    fun addTask() {
        val taskList = _clickedNote.value?.taskList?.toMutableList() ?: mutableListOf()


        taskList.add(TaskDomainModel(noteId = _clickedNote.value?.noteId ?: 0))

        _clickedNote.value = _clickedNote.value?.copy(
            taskList = taskList
        )
    }

    fun setTask(task: TaskDomainModel) {
        val updatedTaskList = _clickedNote.value
            ?.taskList
            ?.toMutableList()
            ?.map {
                if (it.localId == task.localId) {
                    it.copy(
                        title = task.title,
                        isDone = task.isDone
                    )
                } else {
                    it
                }
            }

            _clickedNote.value = _clickedNote.value?.copy(taskList = updatedTaskList ?: emptyList())
    }

    fun deleteTask(task: TaskDomainModel) {
        val updatedTask = _clickedNote.value?.taskList
            ?.filter { it.taskId != task.taskId }

        updatedTask?.let {
            _clickedNote.value = _clickedNote.value?.copy(
                taskList = it
            )
        }
    }

    fun setTitle(text: String) {
        _clickedNote.value = _clickedNote.value?.copy(title = text)
    }

    fun setContent(text: String) {
        _clickedNote.value = _clickedNote.value?.copy(content = text)
    }

    fun setColor(color: Color) {
        _clickedNote.value = _clickedNote.value?.copy(color = color)
    }

    fun setDialogVisibleChangeColor(isOpen: Boolean) {
        _isOpenDialogChangeColor.value = isOpen
    }


    fun searchNote(text: String) {
        val searchText = text.trim().lowercase().split(" ").filter { it.isNotBlank() }

        _noteList.value = _noteList.value.filter { note ->
            val title = note.title.lowercase()
            val content = note.content.lowercase()

            searchText.any {
                title.contains(it) || content.contains(it)
            }
        }
    }

    fun setSelectedButton(button: GroupItemsByLocation) {
        if (_isSelectedFilteredButton.value != null && _isSelectedFilteredButton.value == button) {
            _isSelectedFilteredButton.value = null
            locationListener.value = ItemLocation.MAIN
        } else {
            _isSelectedFilteredButton.value = button
            locationListener.value = button.location
        }
    }

    fun clearDeletedNotes() {
        viewModelScope.launch (Dispatchers.IO) {
            val deletedNotes = _noteList.value.filter { it.location == ItemLocation.DELETED }

            deletedNotes.forEach { note ->
                noteUseCases.deleteNoteUseCase(note.toNote())
            }

            _noteList.value = _noteList.value.filter { it.location != ItemLocation.DELETED }

        }
    }
}