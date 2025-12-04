package white.ball.domain.model

import white.ball.domain.extension_model.ItemStatus

data class TagDomainModel(
    val tagId: Long,
    val title: String,
    val imageResId: Int,
    val status: ItemStatus,
    val price: Int,
)
