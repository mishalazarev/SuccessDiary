package white.ball.success_diary.presentation.util

import androidx.compose.ui.graphics.Color
import white.ball.domain.extension_model.NoteColor
import white.ball.success_diary.presentation.ui.theme.NoteBlueColor
import white.ball.success_diary.presentation.ui.theme.NoteGreenColor
import white.ball.success_diary.presentation.ui.theme.NotePinkColor
import white.ball.success_diary.presentation.ui.theme.NotePurpleColor
import white.ball.success_diary.presentation.ui.theme.NoteRedColor
import white.ball.success_diary.presentation.ui.theme.NoteWhiteColor
import white.ball.success_diary.presentation.ui.theme.NoteYellowColor

fun NoteColor.toColorUI(): Color = when (this) {
    NoteColor.PINK -> NotePinkColor
    NoteColor.RED -> NoteRedColor
    NoteColor.GREEN -> NoteGreenColor
    NoteColor.YELLOW -> NoteYellowColor
    NoteColor.BLUE -> NoteBlueColor
    NoteColor.PURPLE -> NotePurpleColor
    NoteColor.WHITE -> NoteWhiteColor
}

fun Color.toDomainColor(): NoteColor = when (this) {
    NotePinkColor -> NoteColor.PINK
    NoteRedColor -> NoteColor.RED
    NoteGreenColor -> NoteColor.GREEN
    NoteYellowColor -> NoteColor.YELLOW
    NoteBlueColor -> NoteColor.BLUE
    NotePurpleColor -> NoteColor.PURPLE
    NoteWhiteColor -> NoteColor.WHITE
    else -> throw IllegalArgumentException("Illegal color")
}