package com.example.handndnotes.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.handndnotes.extensions.HandyBorderOptions.Side

@DslMarker
annotation class HandyBorderDsl

@HandyBorderDsl
class HandyBorderOptions {
    //All hidden by default
    val top = side()
    val bottom = side()
    val start = side()
    val end = side()

    fun all(strokeWidth: Dp, color: Color) = applyTo(strokeWidth, color, top, bottom, start, end)
    fun topAndBottom(strokeWidth: Dp, color: Color) = applyTo(strokeWidth, color, top, bottom)
    fun startAndEnd(strokeWidth: Dp, color: Color) = applyTo(strokeWidth, color, start, end)

    private fun applyTo(strokeWidth: Dp, color: Color, vararg sides: Side) =
        sides.forEach {
           it.apply(strokeWidth,color)
        }

    class Side(strokeWidth: Dp, var color: Color) {
        var strokeWidth = strokeWidth
            set(value) {
                field = value
                halfWidth = value /2
            }

        //drawLine draws from center of given point, need this offset so we're inside the composable
        var halfWidth: Dp = strokeWidth / 2
            private set

        fun visible() = strokeWidth != Dp.Unspecified && color != Color.Transparent
        fun apply(strokeWidth: Dp, color: Color){
            this.strokeWidth = strokeWidth
            this.color = color
        }
    }

    private fun side() = Side(Dp.Unspecified, Color.Transparent)
}

//This might be really resource intensive. Just sayin'
fun Modifier.handyBorder(builder: HandyBorderOptions.() -> Unit): Modifier = drawWithContent {
    drawContent()

    fun Side.draw(startX: Float, startY: Float, endX: Float, endY: Float) {
        if (!visible()) return
        drawLine(color = color, strokeWidth = strokeWidth.toPx(), start = Offset(startX, startY), end = Offset(endX, endY))
    }

    with(HandyBorderOptions().apply(builder)) {
        top.draw(0f, top.halfWidth.toPx(), size.width, top.halfWidth.toPx())
        bottom.draw(0f, size.height - bottom.halfWidth.toPx(), size.width, size.height - bottom.halfWidth.toPx())
        start.draw(start.halfWidth.toPx(), 0f, start.halfWidth.toPx(), size.height)
        end.draw(size.width - end.halfWidth.toPx(), 0f, size.width - end.halfWidth.toPx(), size.height)
    }


}



