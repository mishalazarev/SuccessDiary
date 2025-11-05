package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.TaskDTO
import white.ball.data.local_storage.room.entity.TimerDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.Tag
import white.ball.domain.model.Task
import white.ball.domain.model.Timer


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

fun NoteDomainModel.toNoteDTO(): NoteDTO = NoteDTO(
    noteId = this.noteId,
    title = this.title,
    content = this.content,
    dateCreated = this.dateCreated,
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
    dateCreated = this.dateCreated,
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