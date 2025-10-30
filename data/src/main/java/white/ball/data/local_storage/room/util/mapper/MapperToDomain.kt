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


fun AchievementDTO.toAchievement(): Achievement = Achievement(
    achievementId = this.achievementId,
    name = this.name,
    isDoneTaskList = this.isDoneTaskList,
    maxEventList = this.maxEventList,
    measurement = this.measurement,
    currentEvent = this.currentEvent,
    maxEvent = this.maxEvent,
    userOwnerId = this.userOwnerId
)

fun NoteDTO.toNote(): Note = Note(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    creationDate = this.creationDate,
    color = this.color,
    location = this.location,
    userOwnerId = this.userOwnerId
)

fun TagDTO.toTag(): Tag = Tag(
    tagId = this.tagId,
    title = this.title,
    status = this.status,
    timer = this.timer.toTimer(),
    price = this.price,
    userOwnerId = this.userOwnerId
)

fun TaskDTO.toTask(): Task = Task(
    taskId = this.taskId,
    title = this.title,
    color = this.color,
    isDone = this.isDone,
    location = this.location,
    creationDate = this.creationDate,
    userOwnerId = this.userOwnerId
)

fun TimerDTO.toTimer(): Timer = Timer(
    timerId = this.timerId,
    maxTime = this.maxTime,
    lefTime = this.leftTime,
    userOwnerId = this.userOwnerId
)

fun UserDTO.toUser(): User = User(
    userId = this.userId,
    status = this.status,
    balance = this.balance,
)

fun UserFullStackDTO.toUserFullStack(): UserFullStack = UserFullStack(
    user = this.userDTO.toUser(),
    taskList = this.taskList.map { it.toTask() }.toList(),
    noteList = this.noteList.map { it.toNote() }.toList(),
    tagList = this.tagList.map { it.toTag() }.toList(),
    achievementList = this.achievementList.map { it.toAchievement() }.toList()
)
