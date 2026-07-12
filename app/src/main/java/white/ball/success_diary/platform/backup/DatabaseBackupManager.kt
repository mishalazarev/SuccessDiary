package white.ball.success_diary.platform.backup

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import white.ball.data.local_storage.room.database.SuccessDiaryDatabase
import white.ball.success_diary.presentation.di.DatabaseModule.NAME_DATABASE
import java.io.File
import java.util.Observable
import java.util.Observer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseBackupManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: SuccessDiaryDatabase,
) {

    suspend fun exportDatabase(uri: Uri) = withContext(Dispatchers.IO) {
        checkpointDatabase()

        val databaseFile = context.getDatabasePath(NAME_DATABASE)
        require(databaseFile.exists()) {
            "Файл базы данных пока не создан"
        }

        context.contentResolver.openOutputStream(uri)?.use { outputStream ->
            databaseFile.inputStream().use { inputStream ->
                inputStream.copyTo(outputStream)
            }
        } ?: error("Не удалось открыть файл для экспорта")
    }

    suspend fun importDatabase(uri: Uri) = withContext(Dispatchers.IO) {
        val databaseFile = context.getDatabasePath(NAME_DATABASE)
        databaseFile.parentFile?.mkdirs()
        val temporaryDatabaseFile = File(databaseFile.parentFile, "$NAME_DATABASE-import")

        if (temporaryDatabaseFile.exists()) {
            temporaryDatabaseFile.delete()
        }

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            temporaryDatabaseFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        } ?: error("Не удалось открыть файл для импорта")

        database.close()
        deleteRoomSideFiles(databaseFile)

        if (!temporaryDatabaseFile.renameTo(databaseFile)) {
            temporaryDatabaseFile.inputStream().use { inputStream ->
                databaseFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            temporaryDatabaseFile.delete()
        }
    }

    private fun checkpointDatabase() {
        database.openHelper.writableDatabase
            .query("PRAGMA wal_checkpoint(TRUNCATE)")
            .use { cursor ->
                cursor.moveToFirst()
            }
    }

    private fun deleteRoomSideFiles(databaseFile: File) {
        listOf(databaseFile, File("${databaseFile.path}-wal"), File("${databaseFile.path}-shm"))
            .forEach { file ->
                if (file.exists()) {
                    file.delete()
                }
            }
    }

}
