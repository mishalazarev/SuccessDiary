package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Music

interface MusicRepository {

    fun getMusicList(): Flow<List<Music>>

    suspend fun insertMusicList(musicList: List<Music>)

    suspend fun insertMusic(music: Music)

    suspend fun updateMusic(music: Music)
}