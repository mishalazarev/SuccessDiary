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
            imageResId = R.drawable.album_atmosphere_of_the_night_sample,
            rawResId = R.raw.music_atmosphere_of_the_night_sample,
            price = 300,
            status = ItemStatus.CLOSE
        ),

        Music (
            musicId = 1,
            title = "Дождь в городе",
            artist = "Без автора",
            imageResId = R.drawable.album_cities_in_the_rain,
            rawResId = R.raw.music_cities_in_the_rain,
            price = 600,
            status = ItemStatus.CLOSE
        ),

        Music (
            musicId = 2,
            title = "Треск огня в камине",
            artist = "Без автора",
            imageResId = R.drawable.album_crackling_fire_fireplace,
            rawResId = R.raw.music_crackling_fire_fireplace,
            price = 600,
            status = ItemStatus.CLOSE
        ),

        Music (
            musicId = 3,
            title = "Отголоски ужаса",
            artist = "Без автора",
            imageResId = R.drawable.album_echoes_of_dread,
            rawResId = R.raw.music_echoes_of_dread,
            price = 600,
            status = ItemStatus.CLOSE
        ),

        Music (
            musicId = 4,
            title = "Звуки ночи",
            artist = "Без автора",
            imageResId = R.drawable.album_sound_of_the_night_song_cicadas_in_the_foreground,
            rawResId = R.raw.music_sound_of_the_night_song_cicadas_in_the_foreground,
            price = 600,
            status = ItemStatus.CLOSE
        ),

        Music (
            musicId = 5,
            title = "Скрытое погружение",
            artist = "Без автора",
            imageResId = R.drawable.album_veiled_descent,
            rawResId = R.raw.music_veiled_descent,
            price = 600,
            status = ItemStatus.CLOSE
        ),

        Music (
            musicId = 6,
            title = "Шепот под дождем",
            artist = "Без автора",
            imageResId = R.drawable.album_whispers_in_the_rain,
            rawResId = R.raw.music_whispers_in_the_rain,
            price = 600,
            status = ItemStatus.CLOSE
        ),
    )
    val musicList: List<Music> = _musicList


    fun buyMusic(titleMusic: String): Music {
        return _musicList
            .filter { it.title == titleMusic }
            .map {
                it.copy(
                    status = ItemStatus.AVAILABLE
                )
            }
            .first()
    }

}