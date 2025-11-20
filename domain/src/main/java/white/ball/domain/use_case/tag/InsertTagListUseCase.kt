package white.ball.domain.use_case.tag

import white.ball.domain.model.Tag
import white.ball.domain.repository.TagRepository
import javax.inject.Inject

class InsertTagListUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {

    suspend operator fun invoke(tagList: List<Tag>) {
        tagRepository.insertTagList(tagList)
    }
}
