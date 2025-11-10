package white.ball.domain.model

import white.ball.domain.extension_model.TagStatus


data class Tag(
    val tagId: Long,
    val title: String,
    var status: TagStatus,
    val timer: Timer,
    val price: Int,
)

