package white.ball.domain.use_case.tag

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Tag
import white.ball.domain.repository.TagRepository
import javax.inject.Inject

class GetTagListUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {
    operator fun invoke(): Flow<List<Tag>> {
        return tagRepository.getTagList()
    }
}