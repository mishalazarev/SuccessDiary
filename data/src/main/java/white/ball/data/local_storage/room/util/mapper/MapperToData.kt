package white.ball.data.local_storage.room.util.mapper

import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.additional.TimerDTO
import white.ball.domain.model.Achievement
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.model.Tag
import white.ball.domain.model.additional.TaskDomainModel
import white.ball.domain.model.additional.Timer


fun Achievement.toAchievementTDO() = AchievementDTO(
    achievementId = this.achievementId,
    name = this.name,
    isDoneTaskList = this.isDoneTaskList,
    maxEventList = this.maxEventList,
    measurement = this.measurement,
    currentEvent = this.currentEvent,
    maxEvent = this.maxEvent,
)

fun NoteDomainModel.toNoteWithTasksDTO(): Pair<NoteDTO, List<TaskDTO>> {
    val noteDtO = NoteDTO(
        noteId = this.noteId,
        title = this.title,
        content = this.content,
        createdDate = this.createdDate,
        color = this.color,
        location = this.location,
    )

    val tasksDTO = this.taskList.map { task ->
        TaskDTO (
            taskId = task.taskId,
            title = task.title,
            isDone = task.isDone,
            noteId = this.noteId
        )
    }

    return Pair(noteDtO, tasksDTO)
}

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

fun TaskDomainModel.toTaskDTO(noteId: Long) = TaskDTO(
    taskId = this.taskId,
    title = this.title,
    isDone = this.isDone,
    noteId = noteId
)