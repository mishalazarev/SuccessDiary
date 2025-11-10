package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.CoffeeCoin

interface CoffeeCoinRepository {
    fun getCoffeeCoins(): Flow<CoffeeCoin?>

    suspend fun createCoffeeCoin(coffeeCoin: CoffeeCoin)

    suspend fun updateBalance(balance: Int)
}