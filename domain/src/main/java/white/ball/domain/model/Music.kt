package white.ball.domain.model

import white.ball.domain.extension_model.ItemStatus

data class Music(
    val musicId: Long,
    val title: String,
    val artist: String,
    val imageResId: Int,
    val rawResId: Int,
    val price: Int,
    var status: ItemStatus,
)
