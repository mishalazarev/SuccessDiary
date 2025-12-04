package white.ball.domain.use_case.music

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import white.ball.domain.model.MusicDomainModel
import white.ball.domain.repository.MusicRepository

class InsertMusicListUseCaseTest {

    private val musicRepository = mock(MusicRepository::class.java)
    private val insertListUseCase = InsertMusicListUseCase(musicRepository)


    @AfterEach
    fun tearDown() {
        Mockito.reset(musicRepository)
    }

    @Test
    fun `update music list`() = runTest {
        val testData: Flow<List<MusicDomainModel>> = flow {
            emit(emptyList())
        }

        val expected = testData.single()

        insertListUseCase(expected)

        verify(musicRepository, Mockito.times(1)).insertMusicList(expected)
    }

}