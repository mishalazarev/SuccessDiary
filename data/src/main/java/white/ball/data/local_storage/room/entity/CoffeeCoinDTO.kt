package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.extension_model.AchievementMeasurement

@Entity
data class CoffeeCoinDTO(
    @PrimaryKey(autoGenerate = true)
    val coffeeCoinId: Long,
    val title: String,
    val balance: Int,
)
