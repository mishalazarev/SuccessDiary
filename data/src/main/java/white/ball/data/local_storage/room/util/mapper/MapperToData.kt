package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.FocusTimeDTO
import white.ball.data.local_storage.room.entity.MusicDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.FocusTime
import white.ball.domain.model.Music
import white.ball.domain.model.Tag
import white.ball.domain.model.additional.TaskByNoteDomainModel



fun Tag.toTagDTO() = TagDTO(
    tagId = this.tagId,
    title = this.title,
    imageResId = this.imageResId,
    status = this.status,
    price = this.price,
)

fun CoffeeCoin.toCoffeeCoinDTO() = CoffeeCoinDTO(
    coffeeCoinId = this.coffeeCoinId,
    title = this.title,
    balance = this.balance,
)

fun TaskByNoteDomainModel.toTaskDTO(noteId: Long) = TaskDTO(
    taskId = this.taskId,
    title = this.title,
    isDone = this.isDone,
    noteId = noteId,
)


fun Music.toMusicDTO() = MusicDTO(
    musicId = this.musicId,
    title = this.title,
    artist = this.artist,
    rawResId = this.rawResId,
    price = this.price,
    status = this.status,
    imageResId = this.imageResId,
)

fun FocusTime.toFocusTimeDTO() = FocusTimeDTO(
    focusTimeId = this.focusTimeId,
    focusTime = this.focusTime,
)