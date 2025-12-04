package white.ball.domain.use_case.music

import white.ball.domain.model.MusicDomainModel
import white.ball.domain.repository.MusicRepository
import javax.inject.Inject

class UpdateMusicUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {

    suspend operator fun invoke(music: MusicDomainModel) {
        musicRepository.updateMusic(music)
    }
}