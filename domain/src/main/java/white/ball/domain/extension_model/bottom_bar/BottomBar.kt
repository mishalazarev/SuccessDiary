package white.ball.domain.extension_model.bottom_bar

import white.ball.domain.R
import white.ball.domain.extension_model.navigation.ScreenNavigation

enum class BottomBar (
    val route: String,
    val iconDefault: Int,
) {
    HOME(ScreenNavigation.MAIN_SCREEN.route, R.drawable.icon_home_bottom_bar_default),
    NOTE_BOOK(ScreenNavigation.NOTE_BOOK_SCREEN.route, R.drawable.icon_note_bottom_bar_default),
    PROFILE(ScreenNavigation.ABOUT_US_SCREEN.route, R.drawable.icon_warning_bottom_bar_default),
}