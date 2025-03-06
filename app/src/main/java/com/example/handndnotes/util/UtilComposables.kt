package com.example.handndnotes.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.util.Predicate
import com.example.handndnotes.smolPadding

@Composable
fun HandySpacer() = Spacer(Modifier)

@Composable
fun HandyIcon(@DrawableRes drawable: Int) {
    Image(painterResource(drawable), "", Modifier.size(50.dp))
}

//move to "Styling"?
@Composable
fun Outline(content: @Composable () -> Unit) {
    Box(
        Modifier
            .border(2.dp, Color.Black)
            .smolPadding()
    ) {
        content()
    }
}

fun Modifier.composedIf(predicate: Boolean, block: Modifier.() -> Modifier) =
        if (predicate)
            block()
        else
            this

