package white.ball.success_diary.presentation.ui.note_book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import white.ball.success_diary.R
import white.ball.success_diary.presentation.model.NoteModelUI

@Composable
fun NoteItemUI(
    note: NoteModelUI
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(note.color, RoundedCornerShape(10.dp)),
    ) {
        Text(
            text = note.title,
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto))
            ),
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = note.creationDate.toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.roboto))
                ),
                modifier = Modifier
                    .padding(end = 10.dp)
            )
        }
    }
}