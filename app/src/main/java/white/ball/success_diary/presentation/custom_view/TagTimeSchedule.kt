package white.ball.success_diary.presentation.custom_view

import android.content.Context
import android.graphics.Canvas
import android.view.View
import android.graphics.Color
import android.graphics.Paint

class TagTimeSchedule(context: Context) : View(context) {

    private val diagramPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    }

    override fun onDraw(canvas: Canvas) {
    }


}