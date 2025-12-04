package white.ball.domain.use_case.tag

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import white.ball.domain.model.TagDomainModel
import white.ball.domain.repository.TagRepository

class GetTagListUseCaseTest {

    private val tagRepository = mock(TagRepository::class.java)
    private val getListUseCase = GetTagListUseCase(tagRepository)

    @AfterEach
    fun tearDown() {
        Mockito.reset(tagRepository)
    }

    @Test
    fun `get tag list`() = runTest {
        val testData: Flow<List<TagDomainModel>> = flow { emit(emptyList()) }

        val expected = testData.single()

        Mockito.`when`(tagRepository.getTagList())
            .thenReturn(testData)

        val actual = getListUseCase().single()

        Mockito.verify(tagRepository, Mockito.times(1))
            .getTagList()

        Assertions.assertEquals(expected, actual)
    }

}