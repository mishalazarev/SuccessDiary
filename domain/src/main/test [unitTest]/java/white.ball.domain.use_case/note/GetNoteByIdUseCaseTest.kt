package white.ball.domain.use_case.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.PageColor
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.repository.NoteRepository

class GetNoteByIdUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val getNoteByIdUseCase = GetNoteByIdUseCase(noteRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `get note by id`() = runTest {
        val testData: Flow<NoteDomainModel> = flow {
            emit(
                NoteDomainModel(
                    noteId = 0,
                    title = "",
                    content = "",
                    createdDate = "",
                    color = PageColor.WHITE,
                    location = ItemLocation.MAIN,
                    taskList = emptyList()
                )
            )
        }

        val expected = testData.single()

        val noteId = expected.noteId

        Mockito.`when`(noteRepository.getNoteWithTasksById(noteId))
            .thenReturn(testData)

        val actual = getNoteByIdUseCase(noteId).single()

        Mockito.verify(noteRepository, Mockito.times(1))
            .getNoteWithTasksById(noteId)

        Assertions.assertEquals(expected, actual)
    }
}