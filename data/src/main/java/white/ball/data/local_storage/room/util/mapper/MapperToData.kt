package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.TimerDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.Tag
import white.ball.domain.model.Timer


fun Achievement.toAchievementTDO() = AchievementDTO(
    achievementId = this.achievementId,
    name = this.name,
    isDoneTaskList = this.isDoneTaskList,
    maxEventList = this.maxEventList,
    measurement = this.measurement,
    currentEvent = this.currentEvent,
    maxEvent = this.maxEvent,
)

fun NoteDomainModel.toNoteDTO() = NoteDTO(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    dateCreated = this.createdDate,
    color = this.color,
    location = this.location,
)

fun Tag.toTagDTO() = TagDTO(
    tagId = this.tagId,
    title = this.title,
    status = this.status,
    timer = this.timer.toTimerDTO(),
    price= this.price,
)

fun Timer.toTimerDTO() = TimerDTO(
    timerId = this.timerId,
    maxTime = this.maxTime,
    leftTime = this.lefTime,
)

fun CoffeeCoin.toCoffeeCoinDTO() = CoffeeCoinDTO(
    coffeeCoinId = this.coffeeCoinId,
    title = this.title,
    balance = this.balance,
)