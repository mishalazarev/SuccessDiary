package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.CoffeeCoinDomainModel

interface CoffeeCoinRepository {
    fun getCoffeeCoins(): Flow<CoffeeCoinDomainModel?>

    suspend fun createCoffeeCoin(coffeeCoinDomainModel: CoffeeCoinDomainModel)

    suspend fun updateBalance(balance: Int)
}