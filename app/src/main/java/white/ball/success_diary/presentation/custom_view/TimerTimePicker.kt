package white.ball.success_diary.presentation.custom_view

import android.content.Context
import android.view.View
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import androidx.compose.ui.graphics.toArgb
import white.ball.domain.extension_model.TimerTime
import white.ball.success_diary.presentation.ui.theme.MainBackgroundColor
import kotlin.text.Typography.times

class TimerTimePicker(
    context: Context,
) : View(context) {

    var onTimeChanged: ((Int) -> Unit)? = null

    private val timerPickerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        strokeWidth = 6f
    }

    private val timerPickerProgressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = MainBackgroundColor.toArgb()
        strokeWidth = 6f
    }

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = MainBackgroundColor.toArgb()
    }

    private val currentTime = 20

    val times = TimerTime.entries.map { it.time }
    private var progress = times.indexOf(currentTime).toFloat() / (times.size - 1)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, 60)
    }

    override fun onDraw(canvas: Canvas) {

        val startX = paddingLeft.toFloat()
        val endX = width - paddingRight.toFloat()
        val centerY = height / 2f

        val thumbX = startX + (endX - startX) * progress

        canvas.drawLine(startX, centerY, endX, centerY, timerPickerPaint)

        canvas.drawLine(startX, centerY, thumbX, centerY, timerPickerProgressPaint)

        canvas.drawCircle(thumbX, centerY, 20f, thumbPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val startX = paddingLeft.toFloat()
        val endX = width - paddingRight.toFloat()

        when(event.action) {
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_DOWN -> {
                progress = ((event.x - startX) / (endX - startX))
                    .coerceIn(0f, 1f)

                val index = (progress * (times.size - 1))
                    .toInt()
                    .coerceIn(0, times.lastIndex)

                val time = times[index]
                onTimeChanged?.invoke(time)
                invalidate()
                return true
            }
        }
        return false
    }

    fun setTime(time: Int) {
        val index = times.indexOf(time)

        if (index != -1) {
            progress = index.toFloat() / (times.size - 1)
            invalidate()
        }
    }
}