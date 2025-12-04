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

        try {
            while (timerSecondsLeft > 0 && !isStopped) {
                val minutes = timerSecondsLeft / 60
                val seconds = timerSecondsLeft % 60

                val timeFormat = String.format("%02d:%02d", minutes, seconds)

                setProgress(
                    workDataOf(
                        TIMER_PROGRESS to timerSecondsLeft,
                    )
                )

                notificationManager.notify(NOTIFICATION_ID, createNotification(timeFormat))

                delay(1_000)
                timerSecondsLeft -= 1
            }

            playSound()
        } catch (e: Exception) {
            notificationManager.cancel(NOTIFICATION_ID)
            stopSound()
            return Result.failure()
        }

        notificationManager.cancel(NOTIFICATION_ID)
        return Result.success()
    }

    private fun playSound() {
        mediaPlayer = MediaPlayer.create(context, R.raw.sound_time_finish)
        mediaPlayer?.setOnCompletionListener {
            stopSound()
        }
        mediaPlayer?.start()

    }

    private fun stopSound() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
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
    }
}