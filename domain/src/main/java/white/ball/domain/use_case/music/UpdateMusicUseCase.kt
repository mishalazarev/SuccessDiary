package white.ball.domain.use_case.music

import white.ball.domain.model.Music
import white.ball.domain.repository.MusicRepository
import javax.inject.Inject

class UpdateMusicUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {

    suspend operator fun invoke(music: Music) {
        musicRepository.updateMusic(music)
    }
}