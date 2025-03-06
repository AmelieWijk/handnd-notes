package com.example.handndnotes.dndspells.Common

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.handndnotes.ui.theme.Brown
import com.example.handndnotes.ui.theme.Green

enum class DndSchools(
    val bg: Brush = Brush.verticalGradient(listOf(Color.White)),
    val textColor: Color = Color.Black
) {
    necromancy(Brush.verticalGradient(listOf(Brown, Green)), textColor = Color.White),
    illusion(),
    conjuration(),

}