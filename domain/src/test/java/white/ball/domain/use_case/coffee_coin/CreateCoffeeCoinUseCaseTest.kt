package white.ball.domain.use_case.coffee_coin

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.repository.CoffeeCoinRepository

class CreateCoffeeCoinUseCaseTest {

    private val coffeeCoinRepository = mock(CoffeeCoinRepository::class.java)
    private val createUseCase = CreateCoffeeCoinUseCase(coffeeCoinRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(coffeeCoinRepository)
    }

    @Test
    fun `create coffee coin`() = runTest {
        val testData = CoffeeCoin(
            coffeeCoinId = 0,
            balance = 0,
        )

        createUseCase(testData)

        Mockito.verify(coffeeCoinRepository, Mockito.times(1)).createCoffeeCoin(testData)
    }

}