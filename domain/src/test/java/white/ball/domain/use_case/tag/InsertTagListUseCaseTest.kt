package white.ball.domain.use_case.tag

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.TagDomainModel
import white.ball.domain.repository.TagRepository

class InsertTagListUseCaseTest {

    private val tagRepository = mock(TagRepository::class.java)
    private val insertListUseCase = InsertTagListUseCase(tagRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(tagRepository)
    }

    @Test
    fun `insert tag list`() = runTest {
        val testData: List<TagDomainModel> = emptyList()

        insertListUseCase(testData)

        Mockito.verify(tagRepository, Mockito.times(1))
            .insertTagList(testData)
    }
}