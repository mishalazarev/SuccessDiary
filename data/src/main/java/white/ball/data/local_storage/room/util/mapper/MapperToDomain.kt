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


fun AchievementDTO.toAchievement(): Achievement = Achievement(
    achievementId = this.achievementId,
    name = this.name,
    isDoneTaskList = this.isDoneTaskList,
    maxEventList = this.maxEventList,
    measurement = this.measurement,
    currentEvent = this.currentEvent,
    maxEvent = this.maxEvent,
)

fun NoteDTO.toNote(): NoteDomainModel = NoteDomainModel(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    createdDate = this.dateCreated,
    color = this.color,
    location = this.location,
)

fun TagDTO.toTag(): Tag = Tag(
    tagId = this.tagId,
    title = this.title,
    status = this.status,
    timer = this.timer.toTimer(),
    price = this.price,
)

fun TimerDTO.toTimer(): Timer = Timer(
    timerId = this.timerId,
    maxTime = this.maxTime,
    lefTime = this.leftTime,
)

fun CoffeeCoinDTO.toCoffeeCoin() = CoffeeCoin(
    coffeeCoinId = this.coffeeCoinId,
    title = this.title,
    balance = this.balance,
)
