package white.ball.domain.use_case.coffee_coin

import white.ball.domain.model.CoffeeCoin
import white.ball.domain.repository.CoffeeCoinRepository
import javax.inject.Inject

class CreateCoffeeCoinUseCase @Inject constructor(
    private val coffeeCoinRepository: CoffeeCoinRepository
) {

    suspend operator fun invoke(coffeeCoin: CoffeeCoin) {
        coffeeCoinRepository.createCoffeeCoin(coffeeCoin)
    }
}