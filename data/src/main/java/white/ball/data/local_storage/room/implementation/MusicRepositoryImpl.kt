package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.MusicDao
import white.ball.data.local_storage.room.entity.toMusic
import white.ball.data.local_storage.room.entity.toMusicDTO
import white.ball.domain.collection.MusicCollection
import white.ball.domain.model.MusicDomainModel
import white.ball.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDao: MusicDao
) : MusicRepository {

    override fun getMusicList(): Flow<List<MusicDomainModel>> {
        return musicDao.getMusicList()
            .map { list ->
                list.map { it.toMusic() }
            }
    }

    override suspend fun insertMusicList(music: List<MusicDomainModel>) {
        musicDao.insertMusicList(
            music.map {
                it.toMusicDTO()
            }
        )
    }

    override suspend fun insertMusic(music: MusicDomainModel) {
        musicDao.insertMusic(
            music.toMusicDTO()
        )
    }

    override suspend fun updateMusic(music: MusicDomainModel) {
        musicDao.updateMusic(
            music.toMusicDTO()
        )
    }
}