package white.ball.domain.use_case.note

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.PageColor
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.repository.NoteRepository

class CreateNoteUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val createUseCase = CreateNoteUseCase(noteRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `create note and return it id`() = runTest {
        val testData = NoteDomainModel(
            noteId = 0,
            title = "",
            content = "",
            createdDate = "",
            color = PageColor.PINK,
            location = ItemLocation.MAIN,
            taskList = emptyList()
        )

        val expected = testData.noteId

        Mockito.`when`(noteRepository.addNote(testData))
            .thenReturn(expected)

        val actual = createUseCase(testData)

        verify(noteRepository, Mockito.times(1))
            .addNote(testData)

        Assertions.assertEquals(expected, actual)
    }

}