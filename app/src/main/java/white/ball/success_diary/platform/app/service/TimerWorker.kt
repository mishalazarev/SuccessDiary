package white.ball.success_diary.platform.app.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.media.MediaPlayer
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
    private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private var mediaPlayer: MediaPlayer? = null

    override suspend fun doWork(): Result {
        createNotificationChannel()

        setForeground(connectToForegroundInfo("Таймер запущен"))

        var timerSecondsLeft = workerParams.inputData.getInt(TIMER_KEY, 1)
        var timerCancelSecondsLeft = workerParams.inputData.getInt(TIMER_CANCEL_KEY, 10)

        try {
            while (timerSecondsLeft > 0 && !isStopped) {
                val minutes = timerSecondsLeft / SIXTY
                val seconds = timerSecondsLeft % SIXTY

                val timeFormat = String.format("%02d:%02d", minutes, seconds)

                setProgress(
                    workDataOf(
                        TIMER_PROGRESS to timerSecondsLeft,
                        TIMER_CANCEL_PROGRESS_KEY to timerCancelSecondsLeft
                    )
                )

                notificationManager.notify(NOTIFICATION_ID, createNotification(timeFormat))

                delay(1_000)
                timerSecondsLeft -= 1
                timerCancelSecondsLeft -= 1
            }
        } catch (e: Exception) {
            notificationManager.cancel(NOTIFICATION_ID)
            return Result.failure()

        }

        playSound()
        delay(1_500)
        stopSound()

        notificationManager.cancel(NOTIFICATION_ID)
        return Result.success()
    }

    private fun playSound() {
        mediaPlayer = MediaPlayer.create(context, R.raw.sound_time_finish)
        mediaPlayer?.apply {
            start()
        }

        mediaPlayer = null
    }

    private fun stopSound() {
        mediaPlayer?.apply {
            stop()
            release()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Таймер",
                NotificationManager.IMPORTANCE_LOW
            )

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun connectToForegroundInfo(text: String): ForegroundInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ForegroundInfo(
                NOTIFICATION_ID,
                createNotification(text),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            ForegroundInfo(
                NOTIFICATION_ID,
                createNotification(text)
            )
        }
    }


    fun createNotification(time: String): Notification {
        return NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setContentTitle("Таймер")
            .setContentText(time)
            .setSmallIcon(R.drawable.success_diary)
            .setOngoing(true)
            .build()
    }


    companion object {
        const val NOTIFICATION_ID = 101
        const val CHANNEL_ID = "timer_channel"

        const val TIMER_KEY = "timer_key"
        const val TIMER_PROGRESS = "timer_progress"

        const val TIMER_CANCEL_KEY = "timer_cancel_key"

        const val TIMER_CANCEL_PROGRESS_KEY = "timer_cancel_progress_key"

        const val SIXTY = 60
    }
}