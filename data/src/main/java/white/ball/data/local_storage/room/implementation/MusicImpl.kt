package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.MusicDao
import white.ball.data.local_storage.room.util.mapper.toMusic
import white.ball.data.local_storage.room.util.mapper.toMusicDTO
import white.ball.domain.model.Music
import white.ball.domain.repository.MusicRepository
import javax.inject.Inject

class MusicImpl @Inject constructor(
    private val musicDao: MusicDao
) : MusicRepository {

    override fun getMusicList(): Flow<List<Music>> {
        return musicDao.getMusicList()
            .map { list ->
                list.map { it.toMusic() }
            }
    }

    override suspend fun insertMusicList(musicList: List<Music>) {
        musicDao.insertMusicList(
            musicList.map { it.toMusicDTO() }
        )
    }

    override suspend fun insertMusic(music: Music) {
        musicDao.insertMusic(music.toMusicDTO())
    }

    override suspend fun updateMusic(music: Music) {
        musicDao.updateMusic(music.toMusicDTO())
    }
}