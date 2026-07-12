package white.ball.domain.collection

import white.ball.domain.R
import white.ball.domain.extension_model.ItemStatus

enum class TagCollection(
    var tagId: Long = 0,
    val title: String,
    val imageResId: Int,
    var status: ItemStatus = ItemStatus.CLOSE,
    var price: Int,
) {

    ANALYTIC(
        title = "Аналитика",
        imageResId = R.drawable.tag_analytic,
        price = 600,
    ),

    CYCLE(
        title = "Велосипед",
        imageResId = R.drawable.tag_cycle,
        price = 900,
    ),

    DRAW(
        title = "Рисование",
        imageResId = R.drawable.tag_draw,
        price = 900,
    ),

    GAME(
        title = "Игра",
        imageResId = R.drawable.tag_game,
        price = 1200,
    ),

    GUITAR(
        title = "Гитара",
        imageResId = R.drawable.tag_guitar,
        price = 900,
    ),

    INTERNET(
        title = "Интернет",
        imageResId = R.drawable.tag_internet,
        price = 2000,
    ),

    MESSAGE(
        title = "Сообщение",
        imageResId = R.drawable.tag_message,
        price = 1500,
    ),

    PIANO(
        title = "Пианино",
        imageResId = R.drawable.tag_piano,
        price = 1500,
    ),

    DUMBBELLS(
        title = "Гантеля",
        status = ItemStatus.AVAILABLE,
        imageResId = R.drawable.tag_power_loads,
        price = 0,
    ),

    SEWING(
        title = "Шитье",
        imageResId = R.drawable.tag_sewing,
        price = 600,
    ),

    ASTRONOMY(
        title = "Астрономия",
        imageResId = R.drawable.tag_solar_system_orbit,
        price = 900,
    ),

    AIR_BALLON(
        title = "Воздушный шар",
        imageResId = R.drawable.tag_balloon,
        price = 600,
    ),

    BEATLE(
        title = "Жук",
        imageResId = R.drawable.tag_beetle,
        price = 900,
    ),

    WALK(
        title = "Прогулка",
        imageResId = R.drawable.tag_walk,
        price = 1500,
    ),

    WATCH(
        title = "Просмотр",
        imageResId = R.drawable.tag_watch,
        price = 2000,
    ),

    YOGA(
        title = "Йога",
        imageResId = R.drawable.tag_yoga,
        price = 600,
    ),

    BOOK(
        title = "Книга",
        status = ItemStatus.AVAILABLE,
        imageResId = R.drawable.tag_book,
        price = 0,
    ),

    BROOMSTICK(
        title = "Метла",
        imageResId = R.drawable.tag_broom_witch,
        price = 900,
    ),

    CANDLE(
        title = "Свечи",
        imageResId = R.drawable.tag_burning_candles,
        price = 600,
    ),

    CACTUS(
        title = "Кактус",
        imageResId = R.drawable.tag_cactus,
        price = 600,
    ),

    CANDIES(
        title = "Сладости",
        imageResId = R.drawable.tag_candies_lollipop,
        price = 1200,
    ),

    MAPS(
        title = "Карты",
        imageResId = R.drawable.tag_card,
        price = 900,
    ),

    POT(
        title = "Котелок",
        imageResId = R.drawable.tag_cauldron,
        price = 1200,
    ),

    CLOWN(
        title = "Клоун",
        imageResId = R.drawable.tag_yoga,
        price = 900,
    ),

    HOKCEY_MASK(
        title = "Хокейная маска}",
        imageResId = R.drawable.tag_hockey_mask,
        price = 900,
    ),

    JELLYFISH(
        title = "Медуза",
        imageResId = R.drawable.tag_jellyfish,
        price = 600,
    ),

    ECLIPSE(
        title = "Затмение",
        imageResId = R.drawable.tag_moon_mist,
        price = 600,
    ),

    PLANETS(
        title = "Планеты",
        imageResId = R.drawable.tag_solar_system_orbit,
        price = 1500,
    ),

    PLANTS(
        title = "Растение",
        imageResId = R.drawable.tag_plant_three,
        price = 600,
    ),

    PUMPKIN(
        title = "Тыква",
        imageResId = R.drawable.tag_pumpkin,
        price = 600,
    ),

    SCREAM(
        title = "Крик",
        imageResId = R.drawable.tag_scream,
        price = 900,
    ),

    SKULL(
        title = "Череп",
        imageResId = R.drawable.tag_skull,
        price = 900,
    ),

    SQUID(
        title = "Кальмар",
        imageResId = R.drawable.tag_squid,
        price = 600,
    ),

    VOODOO_DOLL(
        title = "Кукла вуду",
        imageResId = R.drawable.tag_voodoo,
        price = 1500,
    ),

    ARM_ZOMBIE(
        title = "Рука зомби",
        imageResId = R.drawable.tag_zombie_hand,
        price = 1200
    );

    companion object {
        fun getImageResId(title: String) = TagCollection.entries
            .first { it.title == title }
            .imageResId
    }
}