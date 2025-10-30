package white.ball.data.local_storage.room.entity.pack

import androidx.room.Embedded
import androidx.room.Relation
import white.ball.data.local_storage.room.entity.additional.AchievementDTO
import white.ball.data.local_storage.room.entity.additional.NoteDTO
import white.ball.data.local_storage.room.entity.additional.TagDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.main.UserDTO

data class UserFullStackDTO(
    @Embedded val userDTO: UserDTO,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val taskList: List<TaskDTO>,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId",
    )
    val noteList: List<NoteDTO>,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val tagList: List<TagDTO>,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId",
    )
    val achievementList: List<AchievementDTO>
)