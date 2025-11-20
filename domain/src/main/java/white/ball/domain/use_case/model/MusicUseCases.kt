package white.ball.domain.use_case.model

import white.ball.domain.use_case.music.GetMusicListUseCase
import white.ball.domain.use_case.music.InsertMusicListUseCase
import white.ball.domain.use_case.music.InsertMusicUseCase
import white.ball.domain.use_case.music.UpdateMusicUseCase
import javax.inject.Inject

class MusicUseCases @Inject constructor(
    val getMusicListUseCase: GetMusicListUseCase,
    val insertMusicListUseCase: InsertMusicListUseCase,
    val insertMusicUseCase: InsertMusicUseCase,
    val updateMusicUseCase: UpdateMusicUseCase,
)