package white.ball.domain.collection

import white.ball.domain.R
import white.ball.domain.extension_model.ItemStatus

enum class MusicCollection (
    var musicId: Long = 0,
    val title: String,
    val artist: String = "Без автора",
    val imageResId: Int,
    val rawResId: Int,
    var status: ItemStatus = ItemStatus.CLOSE,
    var price: Int,
) {

    ATMOSPHERE_NIGHT(
            title = "Атмосфера ночи",
            imageResId = R.drawable.album_atmosphere_of_the_night_sample,
            rawResId = R.raw.music_atmosphere_of_the_night_sample,
            price = 300,
        ),

        RAIN_IN_CITY(
            title = "Дождь в городе",
            imageResId = R.drawable.album_cities_in_the_rain,
            rawResId = R.raw.music_cities_in_the_rain,
            price = 0,
            status = ItemStatus.AVAILABLE
        ),

        CRACKLE_FIRE_IN_FIREPLACE(
            title = "Треск огня в камине",
            imageResId = R.drawable.album_crackling_fire_fireplace,
            rawResId = R.raw.music_crackling_fire_fireplace,
            price = 600,
        ),

        ECHOES_OF_HORROR(
            title = "Отголоски ужаса",
            imageResId = R.drawable.album_echoes_of_dread,
            rawResId = R.raw.music_echoes_of_dread,
            price = 600,
        ),

        SOUND_NIGHT(
            title = "Звуки ночи",
            imageResId = R.drawable.album_sound_of_the_night_song_cicadas_in_the_foreground,
            rawResId = R.raw.music_sound_of_the_night_song_cicadas_in_the_foreground,
            price = 600,
        ),

        HIDDEN_DIVING(
            title = "Скрытое погружение",
            imageResId = R.drawable.album_veiled_descent,
            rawResId = R.raw.music_veiled_descent,
            price = 600,
        ),

        WHISPER_IN_RAIN(
            title = "Шепот под дождем",
            imageResId = R.drawable.album_whispers_in_the_rain,
            rawResId = R.raw.music_whispers_in_the_rain,
            price = 600,
        );

    companion object {

        fun getRawResId(title: String) = MusicCollection.entries
            .first { it.title == title }
            .rawResId

        fun getImageResId(title: String) = MusicCollection.entries
            .first { it.title == title }
            .imageResId


    }
}