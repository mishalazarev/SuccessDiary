package white.ball.domain.use_case.tag

import white.ball.domain.collection.TagCollection
import white.ball.domain.model.TagDomainModel
import white.ball.domain.repository.TagRepository
import javax.inject.Inject

class InsertTagUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {
    suspend operator fun invoke(tag: TagDomainModel) {
        tagRepository.insertTag(tag)
    }
}