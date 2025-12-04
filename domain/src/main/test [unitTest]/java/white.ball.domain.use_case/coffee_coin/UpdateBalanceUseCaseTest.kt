package white.ball.domain.use_case.coffee_coin

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.repository.CoffeeCoinRepository

class UpdateBalanceUseCaseTest {

    private val coffeeCoinRepository = mock(CoffeeCoinRepository::class.java)
    private val updateUseCase = UpdateBalanceUseCase(coffeeCoinRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(coffeeCoinRepository)
    }

    @Test
    fun `update balance`() = runBlocking {
        val testData = CoffeeCoin(
            coffeeCoinId = 0,
            balance = 0,
        )

        updateUseCase(testData.balance)

        Mockito.verify(coffeeCoinRepository, Mockito.times(1))
            .updateBalance(testData.balance)
    }

}
