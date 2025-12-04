package white.ball.domain.use_case.coffee_coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.repository.CoffeeCoinRepository

class GetCoffeeCoinsUseCaseTest {

    private val coffeeCoinRepository = mock(CoffeeCoinRepository::class.java)
    private val getUseCase = GetCoffeeCoinsUseCase(coffeeCoinRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(coffeeCoinRepository)
    }

    @Test
    fun `get coffee coins if to for first time create`() = runTest {
        val testData = CoffeeCoin(
                    coffeeCoinId = 0,
                    balance = 0,
                )

        val flowExpected: Flow<CoffeeCoin?> = flow { emit(testData) }

        val expected = flowExpected.single()

        Mockito.`when`(coffeeCoinRepository.getCoffeeCoins())
            .thenReturn(flowExpected)

        val flowActual = getUseCase()

        val actual = flowActual.single()

        Mockito.verify(coffeeCoinRepository, Mockito.times(1)).getCoffeeCoins()

        Assertions.assertEquals(expected, actual)
    }

}