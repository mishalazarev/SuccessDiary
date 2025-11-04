package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import white.ball.data.local_storage.room.entity.main.UserDTO
import white.ball.domain.extension_model.NoteColor
import white.ball.domain.extension_model.NoteLocation

@Entity("note",
    foreignKeys = [
        ForeignKey(
            entity = UserDTO::class,
            parentColumns = ["userId"],
            childColumns = ["userOwnerId"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class NoteDTO(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long,
    var title: String,
    var content: String,
    var creationDate: Long,
    var color: NoteColor,
    var location: NoteLocation,
    val userOwnerId: Long
)
