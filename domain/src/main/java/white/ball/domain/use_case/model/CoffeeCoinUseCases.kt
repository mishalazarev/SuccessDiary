package white.ball.domain.use_case.model

import white.ball.domain.use_case.coffee_coin.CreateCoffeeCoinUseCase
import white.ball.domain.use_case.coffee_coin.GetCoffeeCoinsUseCase
import white.ball.domain.use_case.coffee_coin.UpdateBalanceUseCase
import javax.inject.Inject

class CoffeeCoinUseCases @Inject constructor(
    val getCoffeeCoinsUseCase: GetCoffeeCoinsUseCase,
    val createCoffeeCoinUseCase: CreateCoffeeCoinUseCase,
    val updateBalanceUseCase: UpdateBalanceUseCase
)