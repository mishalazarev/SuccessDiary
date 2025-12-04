package white.ball.domain.use_case.music

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.Music
import white.ball.domain.repository.MusicRepository

class GetMusicListUseCaseTest {

    private val musicRepository = mock(MusicRepository::class.java)
    private val getListUseCase = GetMusicListUseCase(musicRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(musicRepository)
    }

    @Test
    fun `get music list`() = runTest {
        val testData: Flow<List<Music>> = flow { emit(emptyList()) }

        val expected = testData.single()

        Mockito.`when`(musicRepository.getMusicList())
            .thenReturn(testData)

        val actual = getListUseCase().single()

        Mockito.verify(musicRepository, Mockito.times(1)).getMusicList()

        Assertions.assertEquals(expected, actual)
    }

}