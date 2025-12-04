package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.CoffeeCoinDao
import white.ball.data.local_storage.room.entity.toCoffeeCoin
import white.ball.data.local_storage.room.entity.toCoffeeCoinDTO
import white.ball.domain.model.CoffeeCoinDomainModel
import white.ball.domain.repository.CoffeeCoinRepository
import javax.inject.Inject


class CoffeeCoinRepositoryImpl @Inject constructor(
    private val coffeeCoinDao: CoffeeCoinDao
) : CoffeeCoinRepository {

    override fun getCoffeeCoins() = coffeeCoinDao
        .getCoffeeCoins()
        .map { it?.toCoffeeCoin() }

    override suspend fun createCoffeeCoin(coffeeCoinDomainModel: CoffeeCoinDomainModel) {
        coffeeCoinDao.createCoffeeCoin(coffeeCoinDomainModel.toCoffeeCoinDTO())
    }

    override suspend fun updateBalance(balance: Int) {
        coffeeCoinDao.updateBalance(balance)
    }
}