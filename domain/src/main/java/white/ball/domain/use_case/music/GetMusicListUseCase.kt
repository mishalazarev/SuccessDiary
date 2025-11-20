package white.ball.domain.use_case.music

import white.ball.domain.repository.MusicRepository
import javax.inject.Inject

class GetMusicListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {

    operator fun invoke() = musicRepository.getMusicList()

}