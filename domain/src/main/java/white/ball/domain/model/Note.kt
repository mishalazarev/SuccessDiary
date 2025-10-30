package white.ball.domain.model

import white.ball.domain.extension_model.NoteLocation


data class Note(
    val noteId: Long,
    var title: String,
    var content: String,
    val creationDate: Long,
    var color: Int,
    var location: NoteLocation,
    val userOwnerId: Long,
)
