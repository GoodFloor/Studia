package com.example.circularprogressbardemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CircularProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var progress: Float = 40f
    private val painter = Paint()
    var defaultWidth: Float = 500f
    var startingPoint: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null)
            return
        painter.color = Color.GREEN
        canvas.drawArc(RectF(0f, 0f, defaultWidth, defaultWidth), startingPoint, (progress / 100f * 360f + startingPoint) % 361f, true, painter)
        painter.color = Color.BLACK
        painter.textSize = 75f
        painter.textAlign = Paint.Align.CENTER
        canvas.drawText("$progress%", width / 2f, height * 0.55f, painter)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(defaultWidth.toInt(), defaultWidth.toInt())
    }
    fun setProgress(p: Float) {
        progress = if (p < 0)
            0f
        else if (p > 100)
            100f
        else
            p
        invalidate()
    }
}