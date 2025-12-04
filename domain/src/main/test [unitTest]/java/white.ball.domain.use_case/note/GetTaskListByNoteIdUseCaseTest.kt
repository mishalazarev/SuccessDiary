package white.ball.domain.use_case.note

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.repository.NoteRepository

class GetTaskListByNoteIdUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val getTaskListByNoteIdUseCaseTest = GetTaskListByNoteIdUseCase(noteRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `get task list by note id`() = runTest {
        val expected: List<TaskByNoteDomainModel> = emptyList()

        val noteId = 0L

        Mockito.`when`(noteRepository.getTaskListByNoteId(noteId))
            .thenReturn(expected)

        val actual = getTaskListByNoteIdUseCaseTest(noteId)

        Mockito.verify(noteRepository, Mockito.times(1))
            .getTaskListByNoteId(noteId)

        Assertions.assertEquals(expected, actual)
    }

}