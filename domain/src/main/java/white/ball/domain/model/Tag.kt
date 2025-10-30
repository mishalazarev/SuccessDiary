package white.ball.domain.model

import white.ball.domain.extension_model.TagStatus


data class Tag(
    val tagId: Long,
    val title: String,
    val status: TagStatus,
    val timer: Timer,
    val price: Int,
    val userOwnerId: Long,
)

