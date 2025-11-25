package white.ball.success_diary.presentation.screen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.CardDefaultColor
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor

@Composable
fun AboutUsScreen(
    versionApp: String
) {

    val url =
        "https://doc-hosting.flycricket.io/success-diary/7a501a1e-350a-46d8-a8e0-0fec51a42a8c/terms"

    val intent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    val context = LocalContext.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .background(MainBackgroundColor)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(10.dp)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "О приложении", style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        ), modifier = Modifier.padding(start = 9.dp, top = 9.dp, bottom = 9.dp)
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 9.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Text(
                        text = "Поддержка | Отправить предложение", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ), modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val recipient = "LazarevMicha@yandex.ru"
                                val subject = "Обратная связь по приложению."
                                val body = """
                        




                    ---
                    Версия приложения: $versionApp
                    """.trimIndent()


                                try {
                                    val showMail = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("mailto:")
                                        putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
                                        putExtra(Intent.EXTRA_SUBJECT, subject)
                                        putExtra(Intent.EXTRA_TEXT, body)
                                    }

                                    context.startActivity(showMail)
                                } catch (e: ActivityNotFoundException) {
                                }
                            }
                            .padding(start = 9.dp, top = 9.dp, bottom = 9.dp))

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 9.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Text(
                        text = "Поставьте нам 5 звезд", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ), modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(start = 9.dp, top = 9.dp, bottom = 9.dp))

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 9.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Text(
                        text = "Условия пользования", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ), modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                context.startActivity(intent)
                            }
                            .padding(start = 9.dp, top = 9.dp, bottom = 9.dp))
                }

                Text(
                    text = "Версия: $versionApp",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic,
                        color = Color.DarkGray,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp),
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}