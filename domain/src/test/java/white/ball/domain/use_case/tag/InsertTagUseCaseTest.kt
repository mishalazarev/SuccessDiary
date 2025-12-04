package white.ball.domain.use_case.tag

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.TagDomainModel
import white.ball.domain.repository.TagRepository

class InsertTagUseCaseTest {

    private val tagRepository = mock(TagRepository::class.java)
    private val insertUseCase = InsertTagUseCase(tagRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(tagRepository)
    }

    @Test
    fun `insert tag`() = runTest {
        val testData = TagDomainModel(
            tagId = 0,
            title = "",
            imageResId = 0,
            price = 0
        )

        insertUseCase(testData)

        Mockito.verify(tagRepository, Mockito.times(1))
            .insertTag(testData)
    }

}