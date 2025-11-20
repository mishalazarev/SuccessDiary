package white.ball.domain.model

import white.ball.domain.extension_model.ItemStatus


data class Tag(
    val tagId: Long,
    val title: String,
    val imageResId: Int,
    var status: ItemStatus = ItemStatus.CLOSE,
    val price: Int,
)

