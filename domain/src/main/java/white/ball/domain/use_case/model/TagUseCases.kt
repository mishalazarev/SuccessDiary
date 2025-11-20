package white.ball.domain.use_case.model

import white.ball.domain.use_case.tag.InsertTagUseCase
import white.ball.domain.use_case.tag.DeleteTagUseCase
import white.ball.domain.use_case.tag.GetTagListUseCase
import white.ball.domain.use_case.tag.InsertTagListUseCase
import white.ball.domain.use_case.tag.UpdateTagUseCase
import javax.inject.Inject

class TagUseCases @Inject constructor(
    val getTagListUseCase: GetTagListUseCase,
    val insertTagUseCase: InsertTagUseCase,
    val insertTagListUseCase: InsertTagListUseCase,
    val updateTagUseCase: UpdateTagUseCase,
    val deleteTagUseCase: DeleteTagUseCase
)