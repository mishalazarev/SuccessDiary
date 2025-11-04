package white.ball.domain.model

import white.ball.domain.extension_model.NoteColor
import white.ball.domain.extension_model.NoteLocation


data class NoteDomainModel(
    val noteId: Long,
    var title: String,
    var content: String,
    val creationDate: Long,
    var color: NoteColor,
    var location: NoteLocation,
    val userOwnerId: Long,
)

