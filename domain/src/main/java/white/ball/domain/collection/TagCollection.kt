package white.ball.domain.collection

import white.ball.domain.R
import white.ball.domain.extension_model.ItemStatus
import white.ball.domain.model.Tag

class TagCollection {

    private val _tagList = mutableListOf(
        Tag(
            tagId = 0,
            title = "Аналитика",
            imageResId = R.drawable.tag_analytic,
            price = 600,
        ),

        Tag(
            tagId = 1,
            title = "Велосипед",
            imageResId = R.drawable.tag_cycle,
            price = 900,
        ),

        Tag(
            tagId = 2,
            title = "Рисование",
            imageResId = R.drawable.tag_draw,
            price = 900,
        ),

        Tag(
            tagId = 3,
            title = "Игра",
            imageResId = R.drawable.tag_game,
            price = 1200,
        ),

        Tag(
            tagId = 4,
            title = "Гитара",
            imageResId = R.drawable.tag_guitar,
            price = 900,
        ),

        Tag(
            tagId = 5,
            title = "Интернет",
            imageResId = R.drawable.tag_internet,
            price = 2000,
        ),

        Tag(
            tagId = 6,
            title = "Сообщение",
            imageResId = R.drawable.tag_message,
            price = 1500,
        ),

        Tag(
            tagId = 7,
            title = "Пианино",
            imageResId = R.drawable.tag_piano,
            price = 1500,
        ),

        Tag(
            tagId = 8,
            title = "gym",
            status = ItemStatus.AVAILABLE,
            imageResId = R.drawable.tag_power_loads,
            price = 0,
        ),

        Tag(
            tagId = 9,
            title = "Шитье",
            imageResId = R.drawable.tag_sewing,
            price = 600,
        ),

        Tag(
            tagId = 10,
            title = "Астрономия",
            imageResId = R.drawable.tag_solar_system_orbit,
            price = 900,
        ),

        Tag(
            tagId = 11,
            title = "Воздушный шар",
            imageResId = R.drawable.tag_balloon,
            price = 600,
        ),

        Tag(
            tagId = 12,
            title = "Жук",
            imageResId = R.drawable.tag_beetle,
            price = 900,
        ),

        Tag(
            tagId = 13,
            title = "Прогулка",
            imageResId = R.drawable.tag_walk,
            price = 1500,
        ),

        Tag(
            tagId = 14,
            title = "Просмотр",
            imageResId = R.drawable.tag_watch,
            price = 2000,
        ),

        Tag(
            tagId = 15,
            title = "Йога",
            imageResId = R.drawable.tag_yoga,
            price = 600,
        ),

        Tag(
            tagId = 16,
            title = "Книга",
            status = ItemStatus.AVAILABLE,
            imageResId = R.drawable.tag_book,
            price = 0,
        ),

        Tag(
            tagId = 17,
            title = "Метла",
            imageResId = R.drawable.tag_broom_witch,
            price = 900,
        ),

        Tag(
            tagId = 18,
            title = "Свечи",
            imageResId = R.drawable.tag_burning_candles,
            price = 600,
        ),

        Tag(
            tagId = 19,
            title = "Кактус",
            imageResId = R.drawable.tag_cactus,
            price = 600,
        ),

        Tag(
            tagId = 20,
            title = "Сладости",
            imageResId = R.drawable.tag_candies_lollipop,
            price = 1200,
        ),

        Tag(
            tagId = 21,
            title = "Карты",
            imageResId = R.drawable.tag_card,
            price = 900,
        ),

        Tag(
            tagId = 22,
            title = "Котелок",
            imageResId = R.drawable.tag_cauldron,
            price = 1200,
        ),

        Tag(
            tagId = 23,
            title = "Клоун",
            imageResId = R.drawable.tag_yoga,
            price = 900,
        ),

        Tag(
            tagId = 24,
            title = "Хокейная маска}",
            imageResId = R.drawable.tag_hockey_mask,
            price = 900,
        ),

        Tag(
            tagId = 25,
            title = "Медуза",
            imageResId = R.drawable.tag_jellyfish,
            price = 600,
        ),

        Tag(
            tagId = 26,
            title = "Затмение",
            imageResId = R.drawable.tag_moon_mist,
            price = 1500,
        ),

        Tag(
            tagId = 27,
            title = "Планеты",
            imageResId = R.drawable.tag_moon_mist,
            price = 600,
        ),

        Tag(
            tagId = 28,
            title = "Растение",
            imageResId = R.drawable.tag_plant_three,
            price = 600,
        ),

        Tag(
            tagId = 29,
            title = "Тыква",
            imageResId = R.drawable.tag_pumpkin,
            price = 600,
        ),

        Tag(
            tagId = 30,
            title = "Крик",
            imageResId = R.drawable.tag_scream,
            price = 900,
        ),

        Tag(
            tagId = 31,
            title = "Череп",
            imageResId = R.drawable.tag_skull,
            price = 900,
        ),

        Tag(
            tagId = 32,
            title = "Кальмар",
            imageResId = R.drawable.tag_squid,
            price = 600,
        ),

        Tag(
            tagId = 33,
            title = "Кукла вуду",
            imageResId = R.drawable.tag_voodoo,
            price = 1500,
        ),

        Tag(
            tagId = 34,
            title = "Рука зомби",
            imageResId = R.drawable.tag_zombie_hand,
            price = 1200
        )
    )

    val tagList: List<Tag> = _tagList

    fun buyTag(titleTag: String): Tag {
        return _tagList
            .filter { titleTag == it.title }
            .map {
                it.copy(
                    price = 0,
                    status = ItemStatus.AVAILABLE
                )
            }
            .first()
    }
}