package white.ball.domain.use_case.tag

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.Tag
import white.ball.domain.repository.TagRepository

class UpdateTagUseCaseTest {

    private val tagRepository = mock(TagRepository::class.java)
    private val updateUseCase = UpdateTagUseCase(tagRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(tagRepository)
    }

    @Test
    fun `update tag`() = runTest {
        val testData = Tag(
            tagId = 0,
            title = "",
            imageResId = 0,
            price = 0,
        )

        updateUseCase(testData)

        Mockito.verify(tagRepository, Mockito.times(1))
            .updateTag(testData)
    }

}