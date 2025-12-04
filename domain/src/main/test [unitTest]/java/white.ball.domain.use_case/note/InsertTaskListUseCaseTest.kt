package white.ball.domain.use_case.note

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.additional.TaskByNoteDomainModel
import white.ball.domain.repository.NoteRepository

class InsertTaskListUseCaseTest {

    private val noteRepository = mock(NoteRepository::class.java)
    private val insertTaskListUseCaseTest = InsertTaskListUseCase(noteRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `insert task list which belong to note`() = runTest {
        val testData: List<TaskByNoteDomainModel> = emptyList()

        insertTaskListUseCaseTest(testData)

        Mockito.verify(noteRepository, Mockito.times(1))
            .insertTaskList(testData)
    }
}