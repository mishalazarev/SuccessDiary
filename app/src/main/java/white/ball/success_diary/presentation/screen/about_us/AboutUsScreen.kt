package white.ball.success_diary.presentation.screen.about_us

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.core.net.toUri
import white.ball.success_diary.R
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AboutUsScreen(
    versionApp: String,
    aboutUsViewModel: AboutUsViewModel = hiltViewModel(),
) {

    val url =
        "https://doc-hosting.flycricket.io/success-diary/7a501a1e-350a-46d8-a8e0-0fec51a42a8c/terms"

    val intent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    val context = LocalContext.current
    val backupState by aboutUsViewModel.backupState.collectAsState()
    var pendingImportUri by remember { mutableStateOf<Uri?>(null) }

    val exportDatabaseLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/octet-stream"),
    ) { uri ->
        uri?.let(aboutUsViewModel::exportDatabase)
    }

    val importDatabaseLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
    ) { uri ->
        pendingImportUri = uri
    }

    LaunchedEffect(backupState.message) {
        backupState.message?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            aboutUsViewModel.clearBackupMessage()
        }
    }

    pendingImportUri?.let { uri ->
        AlertDialog(
            onDismissRequest = { pendingImportUri = null },
            title = {
                Text(text = "Импорт данных")
            },
            text = {
                Text(text = "Текущие заметки, прогресс и CoffeeCoin будут заменены данными из выбранного файла.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        pendingImportUri = null
                        aboutUsViewModel.importDatabase(uri) {
                            restartApp(context)
                        }
                    },
                ) {
                    Text(text = "Импортировать")
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingImportUri = null }) {
                    Text(text = "Отмена")
                }
            },
        )
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .background(MainBackgroundColor)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 14.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                AboutSectionCard {
                    Text(
                        text = "Резервное копирование", style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    )

                    Text(
                        text = "Переносите данные между устройствами",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        ),
                        modifier = Modifier.padding(top = 6.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        BackupActionButton(
                            title = "Импорт",
                            subtitle = "Загрузить файл",
                            iconResId = white.ball.domain.R.drawable.icon_import_file_default,
                            enabled = !backupState.isLoading,
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            importDatabaseLauncher.launch(
                                arrayOf(
                                    "application/octet-stream",
                                    "application/x-sqlite3",
                                    "*/*"
                                )
                            )
                        }

                        BackupActionButton(
                            title = "Экспорт",
                            subtitle = "Сохранить файл",
                            iconResId = white.ball.domain.R.drawable.icon_export_file_default,
                            enabled = !backupState.isLoading,
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            exportDatabaseLauncher.launch(createBackupFileName())
                        }
                    }

                    if (backupState.isLoading) {
                        Text(
                            text = "Выполняю операцию...",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray,
                                fontFamily = FontFamily(Font(R.font.roboto))
                            ),
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }

                AboutSectionCard {
                    Text(
                        text = "О приложении", style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    )

                    Column(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        AboutActionRow(
                            title = "Поддержка | Отправить предложение",
                            showDivider = false,
                        ) {
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

                        AboutActionRow(
                            title = "Поставьте нам 5 звезд",
                        ) {
                            try {
                                val appLink = "https://www.rustore.ru/catalog/app/white.ball.success_diary"
                                val uri = appLink.toUri()

                                val showMarket = Intent(Intent.ACTION_VIEW, uri)
                                context.startActivity(showMarket)
                            } catch (e: ActivityNotFoundException) {
                            }
                        }

                        AboutActionRow(
                            title = "Условия пользования",
                        ) {
                            context.startActivity(intent)
                        }
                    }
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

@Composable
private fun AboutSectionCard(
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        content = content,
    )
}

@Composable
private fun BackupActionButton(
    title: String,
    subtitle: String,
    iconResId: Int,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF5F5F5))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = title,
            modifier = Modifier.size(44.dp)
        )

        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.roboto))
            ),
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center,
        )

        Text(
            text = subtitle,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.roboto))
            ),
            modifier = Modifier.padding(top = 2.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun AboutActionRow(
    title: String,
    showDivider: Boolean = true,
    onClick: () -> Unit,
) {
    if (showDivider) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFE6E6E6)
        )
    }

    Text(
        text = title,
        style = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.roboto))
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp)
    )
}

private fun createBackupFileName(): String {
    val date = SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.getDefault()).format(Date())
    return "success_diary_backup_$date.db"
}

private fun restartApp(context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    } ?: return

    context.startActivity(intent)
    Runtime.getRuntime().exit(0)
}
