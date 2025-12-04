package white.ball.domain.use_case.note

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.extension_model.ItemLocation
import white.ball.domain.extension_model.PageColor
import white.ball.domain.model.NoteDomainModel
import white.ball.domain.repository.NoteRepository

class EditNoteUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val editUseCase = EditNoteUseCase(noteRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `edit note`() = runTest {
        val testData = NoteDomainModel(
            noteId = 0,
            title = "",
            content = "",
            createdDate = "",
            color = PageColor.WHITE,
            location = ItemLocation.MAIN,
            taskList = emptyList()
        )

        editUseCase(testData)

        Mockito.verify(noteRepository, Mockito.times(1))
            .editNote(testData)
    }

}