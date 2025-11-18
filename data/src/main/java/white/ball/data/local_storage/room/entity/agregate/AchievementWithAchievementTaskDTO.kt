package white.ball.data.local_storage.room.entity.agregate

import androidx.room.Embedded
import androidx.room.Relation
import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.additional.AchievementTaskDTO


data class AchievementWithAchievementTaskDTO(
    @Embedded
    val achievementDTO: AchievementDTO,

    @Relation(
        parentColumn = "achievementId",
        entityColumn = "achievementId",
    )
    val achievementTaskDTO: List<AchievementTaskDTO>,
)
