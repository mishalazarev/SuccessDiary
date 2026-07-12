package white.ball.success_diary.presentation.screen.about_us

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import white.ball.success_diary.platform.backup.DatabaseBackupManager
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(
    private val databaseBackupManager: DatabaseBackupManager,
) : ViewModel() {

    private val _backupState = MutableStateFlow(BackupState())
    val backupState: StateFlow<BackupState> = _backupState.asStateFlow()

    fun exportDatabase(uri: Uri) {
        runBackupAction {
            databaseBackupManager.exportDatabase(uri)
            "Данные экспортированы"
        }
    }

    fun importDatabase(uri: Uri, onSuccess: () -> Unit) {
        runBackupAction {
            databaseBackupManager.importDatabase(uri)
            onSuccess()
            "Данные импортированы"
        }
    }

    fun clearBackupMessage() {
        _backupState.update { it.copy(message = null) }
    }

    private fun runBackupAction(action: suspend () -> String) {
        viewModelScope.launch {
            _backupState.update { it.copy(isLoading = true, message = null) }

            val message = try {
                action()
            } catch (exception: Exception) {
                exception.message ?: "Не удалось выполнить операцию"
            }

            _backupState.update {
                it.copy(isLoading = false, message = message)
            }
        }
    }
}

data class BackupState(
    val isLoading: Boolean = false,
    val message: String? = null,
)
