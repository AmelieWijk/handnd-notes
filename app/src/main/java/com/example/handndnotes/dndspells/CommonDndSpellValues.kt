package com.example.handndnotes.dndspells

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import com.example.handndnotes.ui.theme.Brown
import com.example.handndnotes.ui.theme.Green
import com.example.handndnotes.ui.theme.Purple40
import com.example.handndnotes.ui.theme.PurpleGrey40

object CommonDndSpellValues {

    object CastingTime {
        const val action = "1 action"
        const val bonus = "1 bonus action"
        const val reaction = "reaction"
    }

    object SpellLevel {
        const val first = "1st"
        const val second = "2nd"
        const val third = "3rd"

    }

    object Duration {
        const val instant = "Instantaneous"
        const val min10 = "10 minutes"
        const val hours24 = "24 hours"
    }

    object Range {
        const val touch = "Touch"
    }




}