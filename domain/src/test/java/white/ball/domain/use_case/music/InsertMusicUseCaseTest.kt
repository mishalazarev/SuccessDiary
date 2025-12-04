package white.ball.domain.use_case.music

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.MusicDomainModel
import white.ball.domain.repository.MusicRepository

class InsertMusicUseCaseTest {

    private val musicRepository = mock(MusicRepository::class.java)
    private val insertUseCase = InsertMusicUseCase(musicRepository)

    @AfterEach
    fun tearDown() {
       Mockito.reset(musicRepository)
    }

    @Test
    fun `update music when user bought`() = runTest {
        val testData: Flow<MusicDomainModel> = flow {
            emit(
                MusicDomainModel(
                    musicId = 0,
                    title = "",
                    artist = "",
                    rawResId = 0,
                    imageResId = 0,
                    price = 0,
                )
            )
        }

        val expected = testData.single()

        insertUseCase(expected)

        Mockito.verify(musicRepository, Mockito.times(1))
            .insertMusic(expected)
    }

}