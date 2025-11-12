package white.ball.data.local_storage.room.entity.agregate

import androidx.room.Embedded
import androidx.room.Relation
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO

data class NoteWithTasksDTO(
    @Embedded
    val note: NoteDTO,

    @Relation(
        parentColumn = "noteId",
        entityColumn = "noteId"
    )
    val taskList: List<TaskDTO>
)
