package white.ball.domain.use_case.music

import white.ball.domain.collection.MusicCollection
import white.ball.domain.model.MusicDomainModel
import white.ball.domain.repository.MusicRepository
import javax.inject.Inject

class InsertMusicListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {

    suspend operator fun invoke(musicCollection: List<MusicDomainModel>) {
        musicRepository.insertMusicList(musicCollection)
    }
}