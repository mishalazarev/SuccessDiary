package white.ball.success_diary.platform.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import white.ball.success_diary.R

class TimerWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(
    context,
    workerParams
) {

    override suspend fun doWork(): Result {
        val mainMinutes = inputData.getInt(MAIN_KEY_MINUTES, DEFAULT_VALUE)
        var secondsForCancel = inputData.getInt(TIME_FOR_CANCEL_KEY, DEFAULT_VALUE)

        val totalSeconds = mainMinutes * SIXTY

        createNotificationChannel()

        setForeground(createForegroundInfo("Осталось $mainMinutes"))

        var secondsLeft = totalSeconds
        while (secondsLeft > ZERO && secondsForCancel > ZERO) {
            val minutesLeft = secondsLeft / SIXTY
            val secondsRem = secondsLeft % SIXTY

            setProgress(workDataOf(MAIN_TIME_LEFT_KEY to secondsLeft))
            setProgress(workDataOf(TIME_FOR_CANCEL_KEY to secondsForCancel))

            val text = String.format("%02d:%02d", minutesLeft, secondsRem)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, buildNotification(text))

            setForeground(createForegroundInfo(text))
            delay(1000L)
            secondsLeft -= 1
            secondsForCancel -= 1
        }

        return Result.success()
    }
    private fun createForegroundInfo(text: String): ForegroundInfo {
        val notification = buildNotification(text)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(
                NOTIFICATION_ID,
                notification,
                FOREGROUND_SERVICE_TYPE_DATA_SYNC
                )
        } else {
            ForegroundInfo(NOTIFICATION_ID, notification)
        }
    }

    private fun buildNotification(text: String) = NotificationCompat
        .Builder(context, CHANNEL_ID)
        .setContentTitle("Таймер")
        .setContentText(text)
        .setSmallIcon(R.drawable.icon_music_play)
        .setOngoing(true)
        .build()
    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                NAME_CHANNEL,
                NotificationManager.IMPORTANCE_LOW
            )

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    companion object {

        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "timer_channel_id"
        const val NAME_CHANNEL = "channel"
        const val MAIN_KEY_MINUTES = "main_key_minutes"

        const val TIME_FOR_CANCEL_KEY = "time_for_cancel"

        const val MAIN_TIME_LEFT_KEY = "main_time_left"

        const val DEFAULT_VALUE = 1
        const val SIXTY = 60

        const val ZERO = 0
    }
}