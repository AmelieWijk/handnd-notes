package com.example.handndnotes.dndspells.Common

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.handndnotes.ui.theme.Brown
import com.example.handndnotes.ui.theme.Green

enum class DndSchools(
    val gradientColors: List<Color> = listOf(Color.White),
    val headerBg: Brush = Brush.verticalGradient(gradientColors),
    val dropDownBg: Brush = Brush.verticalGradient(gradientColors.reversed()),
    val textColor: Color = Color.Black
) {
    necromancy(gradientColors = listOf(Brown,Green), textColor = Color.LightGray),
    illusion(),
    conjuration(),

}