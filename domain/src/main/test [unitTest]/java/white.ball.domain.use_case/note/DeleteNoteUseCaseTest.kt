package white.ball.domain.use_case.note

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.PageColor
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.repository.NoteRepository

class DeleteNoteUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val deleteUseCase = DeleteNoteUseCase(noteRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `delete note`() = runTest {
        val testData = NoteDomainModel(
            noteId = 0,
            title = "",
            content = "",
            createdDate = "",
            color = PageColor.WHITE,
            location = ItemLocation.MAIN,
            taskList = emptyList()
        )

        deleteUseCase(testData)

        verify(noteRepository, Mockito.times(1))
            .deleteNote(testData)
    }

}