package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.CoffeeCoinDao
import white.ball.data.local_storage.room.util.mapper.toCoffeeCoin
import white.ball.data.local_storage.room.util.mapper.toCoffeeCoinDTO
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.repository.CoffeeCoinRepository
import javax.inject.Inject


class CoffeeCoinImpl @Inject constructor(
    private val coffeeCoinDao: CoffeeCoinDao
) : CoffeeCoinRepository {

    override fun getCoffeeCoins() = coffeeCoinDao
        .getCoffeeCoins()
        .map { it?.toCoffeeCoin() }

    override suspend fun createCoffeeCoin(coffeeCoin: CoffeeCoin) {
        coffeeCoinDao.createCoffeeCoin(coffeeCoin.toCoffeeCoinDTO())
    }

    override suspend fun updateBalance(balance: Int) {
        coffeeCoinDao.updateBalance(balance)
    }
}