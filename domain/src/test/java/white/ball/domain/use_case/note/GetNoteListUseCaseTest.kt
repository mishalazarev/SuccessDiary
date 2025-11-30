package white.ball.domain.use_case.note

import androidx.room.Entity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.repository.NoteRepository

class GetNoteListUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val getListUseCase = GetNoteListUseCase(noteRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `get all notes`() = runTest {
        val testData: Flow<List<NoteDomainModel>> = flow { emit(emptyList()) }

        val expected = testData.single()

        Mockito.`when`(noteRepository.getNoteList())
            .thenReturn(testData)

        val actual = getListUseCase().single()

        Mockito.verify(noteRepository, Mockito.times(1))
            .getNoteList()

        Assertions.assertEquals(expected, actual)
    }

}