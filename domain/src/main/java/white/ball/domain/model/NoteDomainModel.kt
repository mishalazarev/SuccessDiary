package white.ball.domain.model

import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.PageColor


data class NoteDomainModel(
    val noteId: Long,
    var title: String,
    var content: String,
    val createdDate: String,
    var color: PageColor,
    var location: ItemLocation,
)

