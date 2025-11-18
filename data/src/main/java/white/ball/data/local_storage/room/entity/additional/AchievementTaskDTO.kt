package white.ball.data.local_storage.room.entity.additional

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AchievementTaskDTO(
    @PrimaryKey(autoGenerate = true)
    val achievementTaskId: Long,
    val achievementId: Long,
    val title: String,
    val isCompleted: Boolean,
    val reward: Int,
)
