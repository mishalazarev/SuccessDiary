package white.ball.domain.use_case.tag

import white.ball.domain.model.Tag
import white.ball.domain.repository.TagRepository
import javax.inject.Inject

class UpdateTagUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {
    suspend operator fun invoke(tag: Tag) {
        tagRepository.updateTag(tag)
    }
}