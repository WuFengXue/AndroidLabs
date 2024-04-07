package com.reinhard.androidlabs.lab.threeD

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * desc
 *
 * @author Reinhard（李剑波）
 * @date 2024/4/1
 */
class ThreeDMoveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleRes) {
    val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.drawCircle(0.5f * width, 0.5f * height, 0.4f * width, bgPaint)
    }
}