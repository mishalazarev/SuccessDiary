package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.NoteDTO

@Entity (
    foreignKeys = [
        ForeignKey(
            entity = NoteDTO::class,
            parentColumns = ["noteId"],
            childColumns = ["noteId"],
            onDelete = CASCADE
        )
    ]
)
data class TaskDTO(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,
    var title: String,
    var isDone: Boolean,
    val noteId: Long,
)
