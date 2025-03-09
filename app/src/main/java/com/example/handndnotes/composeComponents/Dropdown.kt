package com.example.handndnotes.composeComponents

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.handndnotes.extensions.handyBorder

//As HandyComponent?
@Composable
@SuppressLint("ModifierParameter")
fun Dropdown(
    header: @Composable RowScope.() -> Unit,
    headerModifier: Modifier = Modifier,
    dropdownModifier: Modifier = Modifier,
    dropdown: @Composable () -> Unit
) {
    val borderColor = Color.DarkGray
    Column {
        var expanded by remember { mutableStateOf(false) }

        Row(
            headerModifier
                .handyBorder {
                    startAndEnd(2.dp, borderColor)
                    top.apply(2.dp, borderColor)
                    if (!expanded) bottom.apply(2.dp, borderColor)
                }
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            header()
        }

        AnimatedVisibility(expanded) {
            Box(
                Modifier
                    .handyBorder {
                        startAndEnd(2.dp, borderColor)
                        bottom.apply(2.dp, borderColor)
                    }
                    .then(dropdownModifier)
                    .smolPadding()
            ) {
                dropdown()
            }
        }
    }
}