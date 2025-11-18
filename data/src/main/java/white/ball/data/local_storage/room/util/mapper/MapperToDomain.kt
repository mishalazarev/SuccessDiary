package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.additional.AchievementTaskDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.additional.TimerDTO
import white.ball.data.local_storage.room.entity.agregate.AchievementWithAchievementTaskDTO
import white.ball.data.local_storage.room.entity.agregate.NoteWithTasksDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.Tag
import white.ball.domain.model.additional.AchievementTask
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.model.additional.Timer


fun AchievementWithAchievementTaskDTO.toAchievement() = Achievement(
    achievementId = achievementDTO.achievementId,
    title = achievementDTO.title,
    imageResId = achievementDTO.imageResId,
    measurement = achievementDTO.measurement,
    current = achievementDTO.current,
    achievementTaskList = achievementTaskDTO.map { it.toAchievementTask() },
)
fun NoteWithTasksDTO.toNote() = NoteDomainModel(
    noteId = note.noteId,
    title = note.title,
    content = note.content,
    createdDate = note.createdDate,
    color = note.color,
    location = note.location,
    taskList = this.taskList.map { it.toTaskDomainModel().copy(noteId = note.noteId) },
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

fun TaskDTO.toTaskDomainModel() = TaskByNoteDomainModel (
    taskId = this.taskId,
    title = this.title,
    isDone = this.isDone,
    noteId = this.noteId,
)

fun TaskByNoteDomainModel.toTaskDTO() = TaskDTO (
    taskId = this.taskId,
    title = this.title,
    isDone = this.isDone,
    noteId = this.noteId,
)

fun NoteDomainModel.toNoteDTO() = NoteDTO(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    createdDate = this.createdDate,
    color = this.color,
    location = this.location,
)

fun AchievementTaskDTO.toAchievementTask() = AchievementTask(
    achievementTaskId = this.achievementTaskId,
    achievementId = this.achievementId,
    title = this.title,
    isCompleted = this.isCompleted,
    reward = this.reward,
)

fun AchievementDTO.toAchievement(achievementTaskDTO: List<AchievementTaskDTO>) = Achievement(
    achievementId = this.achievementId,
    title = this.title,
    imageResId = this.imageResId,
    measurement = this.measurement,
    current = current,
    achievementTaskList = achievementTaskDTO.map { it.toAchievementTask() },
)
