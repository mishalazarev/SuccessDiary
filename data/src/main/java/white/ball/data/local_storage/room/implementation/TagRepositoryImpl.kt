package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.TagDao
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.toTag
import white.ball.data.local_storage.room.entity.toTagDTO
import white.ball.domain.collection.TagCollection
import white.ball.domain.model.TagDomainModel
import white.ball.domain.repository.TagRepository
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagDao: TagDao
) : TagRepository {

    override fun getTagList(): Flow<List<TagDomainModel>> {
        return tagDao.getTagList().map { list ->
            list.map { dto -> dto.toTag() }
        }
    }

    override suspend fun insertTag(tag: TagDomainModel) {
        tagDao.insertTag(tag.toTagDTO())
    }

    override suspend fun insertTagList(tag: List<TagDomainModel>) {
        val tagDTOList = mutableListOf<TagDTO>()

        tag.forEach {
            tagDTOList.add(
                it.toTagDTO()
            )
        }

        tagDao.insertTagList(tagDTOList)
    }

    override suspend fun updateTag(tag: TagDomainModel) {
        tagDao.updateTag(tag.toTagDTO())
    }

    override suspend fun deleteTag(tag: TagDomainModel) {
        tagDao.deleteTag(tag.toTagDTO())
    }

}