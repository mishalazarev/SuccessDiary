package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.NoteColor
import white.ball.domain.extension_model.NoteLocation

@Entity("note",)
data class NoteDTO(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long,
    var title: String,
    var content: String,
    var dateCreated: String,
    var color: NoteColor,
    var location: NoteLocation,
)
