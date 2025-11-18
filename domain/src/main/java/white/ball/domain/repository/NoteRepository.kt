package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.additional.TaskByNoteDomainModel

interface NoteRepository {
    fun getNoteList(): Flow<List<NoteDomainModel>>

    suspend fun addNote(noteModelUI: NoteDomainModel): Long

    suspend fun deleteNote(noteModelUI: NoteDomainModel)

    suspend fun deleteTask(taskByNoteDomainModel: TaskByNoteDomainModel)

    suspend fun editNote(noteModelUI: NoteDomainModel)

    fun getNoteWithTasksById(noteId: Long): Flow<NoteDomainModel>

    fun getTaskListByNoteId(noteId: Long): List<TaskByNoteDomainModel>

    suspend fun insertTask(taskByNoteDomainModel: TaskByNoteDomainModel)

    suspend fun insertTaskList(taskByNoteDomainModel: List<TaskByNoteDomainModel>)
}