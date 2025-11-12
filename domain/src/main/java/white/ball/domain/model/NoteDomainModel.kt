package white.ball.domain.model

import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.PageColor
import white.ball.domain.model.additional.TaskDomainModel


data class NoteDomainModel(
    val noteId: Long,
    var title: String,
    var content: String,
    val createdDate: String,
    var color: PageColor,
    var location: ItemLocation,
    var taskList: List<TaskDomainModel>
)

