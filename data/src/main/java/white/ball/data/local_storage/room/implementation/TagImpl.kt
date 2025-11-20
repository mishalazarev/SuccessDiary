package white.ball.data.local_storage.room.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import white.ball.data.local_storage.room.dao.TagDao
import white.ball.data.local_storage.room.util.mapper.toTag
import white.ball.data.local_storage.room.util.mapper.toTagDTO
import white.ball.domain.model.Tag
import white.ball.domain.repository.TagRepository
import javax.inject.Inject

class TagImpl @Inject constructor(
    private val tagDao: TagDao
) : TagRepository {

    override fun getTagList(): Flow<List<Tag>> {
        return tagDao.getTagList().map { list ->
            list.map { it.toTag() }
        }
    }

    override suspend fun insertTag(tag: Tag) {
        val tagDTO = tag.toTagDTO()
        tagDao.insertTag(tagDTO)
    }

    override suspend fun insertTagList(tagList: List<Tag>) {
        val tagDTOList = tagList.map { it.toTagDTO() }
        tagDao.insertTagList(tagDTOList)
    }

    override suspend fun updateTag(tag: Tag) {
        val tagDTO = tag.toTagDTO()
        tagDao.updateTag(tagDTO)
    }

    override suspend fun deleteTag(tag: Tag) {
        val tagDTO = tag.toTagDTO()
        tagDao.deleteTag(tagDTO)
    }

}