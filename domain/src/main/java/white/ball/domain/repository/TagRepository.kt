package white.ball.domain.repository

import kotlinx.coroutines.flow.Flow
import white.ball.domain.model.Tag

interface TagRepository {
    fun getTagList(): Flow<List<Tag>>

    suspend fun insertTag(tag: Tag)

    suspend fun insertTagList(tagList: List<Tag>)

    suspend fun updateTag(tag: Tag)

    suspend fun deleteTag(tag: Tag)
}