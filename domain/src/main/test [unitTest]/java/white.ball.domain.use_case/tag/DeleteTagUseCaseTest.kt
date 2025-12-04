package white.ball.domain.use_case.tag

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.Tag
import white.ball.domain.repository.TagRepository

class DeleteTagUseCaseTest {

    private val tagRepository = mock(TagRepository::class.java)
    private val deleteUseCase = DeleteTagUseCase(tagRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(tagRepository)
    }

    @Test
    fun `delete tag`() = runTest {
        val testData = Tag(
            tagId = 0,
            title = "",
            imageResId = 0,
            price = 0
        )

        deleteUseCase(testData)

        Mockito.verify(tagRepository, Mockito.times(1))
            .deleteTag(testData)
    }

}