package white.ball.data.local_storage.room.entity.main

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDTO(
    @PrimaryKey(autoGenerate = true)
    val userId: Long,
    val status: Int,
    val balance: Int,
)