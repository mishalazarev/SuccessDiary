package white.ball.domain.collection

import white.ball.domain.R
import white.ball.domain.extension_model.ItemStatus
import white.ball.domain.model.Music

class MusicCollection {




    private val _musicList = mutableListOf(
        Music (
            musicId = 0,
            title = "Атмосфера ночи",
            artist = "Без автора",
            imageResId = R.drawable.album_music_atmosphere_of_the_night_sample,
            rawResId = R.raw.music_atmosphere_of_the_night_sample,
            price = 300,
            status = ItemStatus.CLOSE
        )
    )
    val musicCollection: List<Music> = _musicList


}