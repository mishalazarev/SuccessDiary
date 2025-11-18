package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.additional.AchievementTaskDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.additional.TimerDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.Tag
import white.ball.domain.model.additional.AchievementTask
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.model.additional.Timer



fun Tag.toTagDTO() = TagDTO(
    tagId = this.tagId,
    title = this.title,
    status = this.status,
    timer = this.timer.toTimerDTO(),
    price = this.price,
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

fun TaskByNoteDomainModel.toTaskDTO(noteId: Long) = TaskDTO(
    taskId = this.taskId,
    title = this.title,
    isDone = this.isDone,
    noteId = noteId,
)

fun Achievement.toAchievementTDO() = AchievementDTO(
    achievementId = this.achievementId,
    title = this.title,
    imageResId = this.imageResId,
    measurement = this.measurement,
    current = this.current,
)

fun AchievementTask.toAchievementTaskDTO() = AchievementTaskDTO(
    achievementTaskId = this.achievementTaskId,
    achievementId = this.achievementId,
    title = this.title,
    isCompleted = this.isCompleted,
    reward = this.reward,
)