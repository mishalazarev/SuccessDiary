package white.ball.success_diary.presentation.util

import androidx.compose.ui.graphics.Color
import white.ball.domain.extension_model.PageColor
import white.ball.success_diary.presentation.ui.theme.PageBlueColor
import white.ball.success_diary.presentation.ui.theme.PageGreenColor
import white.ball.success_diary.presentation.ui.theme.PagePinkColor
import white.ball.success_diary.presentation.ui.theme.PagePurpleColor
import white.ball.success_diary.presentation.ui.theme.PageRedColor
import white.ball.success_diary.presentation.ui.theme.PageWhiteColor
import white.ball.success_diary.presentation.ui.theme.PageYellowColor

fun PageColor.toColorUI(): Color = when (this) {
    PageColor.PINK -> PagePinkColor
    PageColor.RED -> PageRedColor
    PageColor.GREEN -> PageGreenColor
    PageColor.YELLOW -> PageYellowColor
    PageColor.BLUE -> PageBlueColor
    PageColor.PURPLE -> PagePurpleColor
    PageColor.WHITE -> PageWhiteColor
}

fun Color.toDomainColor(): PageColor = when (this) {
    PagePinkColor -> PageColor.PINK
    PageRedColor -> PageColor.RED
    PageGreenColor -> PageColor.GREEN
    PageYellowColor -> PageColor.YELLOW
    PageBlueColor -> PageColor.BLUE
    PagePurpleColor -> PageColor.PURPLE
    PageWhiteColor -> PageColor.WHITE
    else -> throw IllegalArgumentException("Illegal color")
}