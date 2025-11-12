package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.PageColor

@Entity("note",)
data class NoteDTO(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long,
    var title: String,
    var content: String,
    var createdDate: String,
    var color: PageColor,
    var location: ItemLocation,
)
