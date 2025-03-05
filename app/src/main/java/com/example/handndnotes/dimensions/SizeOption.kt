package com.example.handndnotes.dimensions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import com.example.handndnotes.dimensions.SizeOption.Fill
import com.example.handndnotes.dimensions.SizeOption.Percentage
import com.example.handndnotes.dimensions.SizeOption.Wrap


sealed class SizeOption {
    data object Fill : SizeOption()
    data object Wrap : SizeOption()

    //Scale from 0-1f
    class Percentage(val value: Float) : SizeOption() {
        init {
            check(value in 0f..1f) { "Value should be in the range of 0-100 (inclusive)" }
        }
    }


}

fun Modifier.resolveSize(size: SizeOption): Modifier {
    return when (size) {
        Fill -> fillMaxWidth()
        Wrap -> wrapContentWidth()
        is Percentage -> fillMaxWidth(size.value)
    }
}