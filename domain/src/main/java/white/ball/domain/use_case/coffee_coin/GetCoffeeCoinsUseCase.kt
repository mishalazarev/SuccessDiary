package white.ball.domain.use_case.coffee_coin

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.repository.CoffeeCoinRepository
import javax.inject.Inject

class GetCoffeeCoinsUseCase @Inject constructor(
    private val coffeeCoinRepository: CoffeeCoinRepository
) {

    operator fun invoke(): Flow<CoffeeCoin?> {
        return coffeeCoinRepository.getCoffeeCoins()
    }
}