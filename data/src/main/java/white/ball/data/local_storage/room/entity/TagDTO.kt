package white.ball.data.local_storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import white.ball.domain.collection.TagCollection
import white.ball.domain.extension_model.ItemStatus
import white.ball.domain.model.TagDomainModel

@Entity(tableName = "tag",)
data class TagDTO(
    @PrimaryKey(autoGenerate = true)
    val tagId: Long = 0,
    val title: String,
    var status: ItemStatus,
    var price: Int,
)

fun TagDomainModel.toTagDTO() = TagDTO(
    tagId = this.tagId,
    title = this.title,
    status = this.status,
    price = this.price,
)

fun TagDTO.toTag(): TagDomainModel {
    return TagDomainModel(
        tagId = this.tagId,
        title = this.title,
        status = this.status,
        price = this.price,
        imageResId = TagCollection.getImageResId(this.title)
    )
}

