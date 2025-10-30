package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.additional.AchievementDTO
import white.ball.data.local_storage.room.entity.additional.NoteDTO
import white.ball.data.local_storage.room.entity.additional.TagDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.additional.TimerDTO
import white.ball.data.local_storage.room.entity.main.UserDTO
import white.ball.data.local_storage.room.entity.pack.UserFullStackDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.Note
import white.ball.domain.model.Tag
import white.ball.domain.model.Task
import white.ball.domain.model.Timer
import white.ball.domain.model.User
import white.ball.domain.model.UserFullStack


fun Achievement.toAchievementTDO(): AchievementDTO = AchievementDTO(
    achievementId = this.achievementId,
    name = this.name,
    isDoneTaskList = this.isDoneTaskList,
    maxEventList = this.maxEventList,
    measurement = this.measurement,
    currentEvent = this.currentEvent,
    maxEvent = this.maxEvent,
    userOwnerId = this.userOwnerId
)

fun Note.toNoteDTO(): NoteDTO = NoteDTO(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    creationDate = this.creationDate,
    color = this.color,
    location = this.location,
    userOwnerId = this.userOwnerId
)

fun Task.toTaskDTO(): TaskDTO = TaskDTO(
    taskId = this.taskId,
    title = this.title,
    color = this.color,
    isDone = this.isDone,
    location = this.location,
    creationDate = this.creationDate,
    userOwnerId = this.userOwnerId
)

fun Tag.toTagDTO(): TagDTO = TagDTO(
    tagId = this.tagId,
    title = this.title,
    status = this.status,
    timer = this.timer.toTimerDTO(),
    price= this.price,
    userOwnerId = this.userOwnerId
)

fun Timer.toTimerDTO(): TimerDTO = TimerDTO(
    timerId = this.timerId,
    maxTime = this.maxTime,
    leftTime = this.lefTime,
    userOwnerId = this.userOwnerId
)

fun User.toUserDTO(): UserDTO = UserDTO (
    userId = this.userId,
    status = this.status,
    balance = this.balance,
)

fun UserFullStack.toUserFullStackDTO(): UserFullStackDTO = UserFullStackDTO(
    userDTO = this.user.toUserDTO(),
    taskList = this.taskList.map { it.toTaskDTO() }.toList(),
    noteList = this.noteList.map { it.toNoteDTO() }.toList(),
    tagList = this.tagList.map { it.toTagDTO() }.toList(),
    achievementList = this.achievementList.map { it.toAchievementTDO() }.toList(),
)