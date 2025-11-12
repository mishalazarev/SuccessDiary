package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.additional.TimerDTO
import white.ball.data.local_storage.room.entity.agregate.NoteWithTasksDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.Tag
import white.ball.domain.model.additional.TaskDomainModel
import white.ball.domain.model.additional.Timer


fun AchievementDTO.toAchievement(): Achievement = Achievement(
    achievementId = this.achievementId,
    name = this.name,
    isDoneTaskList = this.isDoneTaskList,
    maxEventList = this.maxEventList,
    measurement = this.measurement,
    currentEvent = this.currentEvent,
    maxEvent = this.maxEvent,
)

fun NoteWithTasksDTO.toNote() = NoteDomainModel(
    noteId = note.noteId,
    title = note.title,
    content = note.content,
    createdDate = note.createdDate,
    color = note.color,
    location = note.location,
    taskList = this.taskList.map { it.toTaskDomainModel().copy(noteId = note.noteId) }
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

fun TaskDTO.toTaskDomainModel() = TaskDomainModel (
    taskId = this.taskId,
    title = this.title,
    isDone = this.isDone,
    noteId = this.noteId
)

fun NoteDomainModel.toNoteDTO() = NoteDTO(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    createdDate = this.createdDate,
    color = this.color,
    location = this.location,
)