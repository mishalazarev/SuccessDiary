package white.ball.domain.use_case.note

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.repository.NoteRepository
import java.util.UUID

class DeleteTaskUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val deleteUseCase = DeleteTaskUseCase(noteRepository)


    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `delete task which belong to note`() = runTest {
        val testData = TaskByNoteDomainModel(
            noteId = 0,
            title = "",
            localId = UUID.randomUUID().leastSignificantBits.toString(),
            isDone = false
        )

        deleteUseCase(testData)

        verify(noteRepository, Mockito.times(1))
            .deleteTask(testData)
    }

}