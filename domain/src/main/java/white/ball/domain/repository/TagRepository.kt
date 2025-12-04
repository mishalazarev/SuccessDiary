package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.collection.TagCollection
import white.ball.domain.model.TagDomainModel

interface TagRepository {
    fun getTagList(): Flow<List<TagDomainModel>>

    suspend fun insertTag(tag: TagDomainModel)

    suspend fun insertTagList(tag: List<TagDomainModel>)

    suspend fun updateTag(tag: TagDomainModel)

    suspend fun deleteTag(tag: TagDomainModel)
}