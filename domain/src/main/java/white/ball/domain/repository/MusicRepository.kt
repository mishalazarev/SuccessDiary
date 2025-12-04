package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.MusicDomainModel

interface MusicRepository {

    fun getMusicList(): Flow<List<MusicDomainModel>>

    suspend fun insertMusicList(music: List<MusicDomainModel>)

    suspend fun insertMusic(music: MusicDomainModel)

    suspend fun updateMusic(music: MusicDomainModel)
}