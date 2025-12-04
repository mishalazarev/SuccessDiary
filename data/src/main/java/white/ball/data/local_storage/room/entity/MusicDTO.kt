package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.collection.MusicCollection
import white.ball.domain.extension_model.ItemStatus
import white.ball.domain.model.MusicDomainModel

@Entity
data class MusicDTO(
    @PrimaryKey(autoGenerate = true)
    val musicId: Long,
    val title: String,
    val artist: String,
    var price: Int,
    var status: ItemStatus,
)

fun MusicDomainModel.toMusicDTO() = MusicDTO(
    musicId = this.musicId,
    title = this.title,
    artist = this.artist,
    price = this.price,
    status = this.status,
)

fun MusicDTO.toMusic() = MusicDomainModel (
    musicId = this.musicId,
    title = this.title,
    artist = this.artist,
    price = this.price,
    status = this.status,
    imageResId = MusicCollection.getImageResId(this.title),
    rawResId = MusicCollection.getRawResId(this.title),
)


