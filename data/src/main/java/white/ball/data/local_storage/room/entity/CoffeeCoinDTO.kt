package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.model.CoffeeCoinDomainModel

@Entity
data class CoffeeCoinDTO(
    @PrimaryKey(autoGenerate = true)
    val coffeeCoinId: Long,
    val title: String,
    val balance: Int,
)

fun CoffeeCoinDTO.toCoffeeCoin() = CoffeeCoinDomainModel(
    coffeeCoinId = this.coffeeCoinId,
    title = this.title,
    balance = this.balance,
)

fun CoffeeCoinDomainModel.toCoffeeCoinDTO() = CoffeeCoinDTO(
    coffeeCoinId = this.coffeeCoinId,
    title = this.title,
    balance = this.balance,
)