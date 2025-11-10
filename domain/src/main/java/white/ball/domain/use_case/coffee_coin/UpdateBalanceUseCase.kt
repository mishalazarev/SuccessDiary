package white.ball.domain.use_case.coffee_coin

import white.ball.domain.repository.CoffeeCoinRepository
import javax.inject.Inject

class UpdateBalanceUseCase @Inject constructor(
    private val coffeeCoinRepository: CoffeeCoinRepository
) {

    suspend operator fun invoke(balance: Int) {
        coffeeCoinRepository.updateBalance(balance)
    }
}