package white.ball.data.local_storage.room.entity.agregate

import androidx.room.Embedded
import androidx.room.Relation
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.additional.TaskByNoteDTO
import white.ball.data.local_storage.room.entity.additional.toTaskDomainModel
import white.ball.domain.model.NoteDomainModel

data class NoteWithTasksDTO(
    @Embedded
    val note: NoteDTO,

    @Relation(
        parentColumn = "noteId",
        entityColumn = "noteId"
    )
    val taskList: List<TaskByNoteDTO>
)

fun NoteWithTasksDTO.toNoteDomainModel() = NoteDomainModel(
    noteId = note.noteId,
    title = note.title,
    content = note.content,
    createdDate = note.createdDate,
    color = note.color,
    location = note.location,
    taskList = this.taskList.map { it.toTaskDomainModel().copy(noteId = note.noteId) },
)
