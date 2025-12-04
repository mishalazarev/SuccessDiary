package white.ball.domain.model

import white.ball.domain.extension_model.ItemStatus

data class MusicDomainModel (
    val musicId: Long,
    val title: String,
    val artist: String,
    val imageResId: Int,
    val rawResId: Int,
    val status: ItemStatus,
    val price: Int,
)