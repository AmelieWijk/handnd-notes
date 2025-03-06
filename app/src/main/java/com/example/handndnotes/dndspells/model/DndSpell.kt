package com.example.handndnotes.dndspells.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.handndnotes.Dropdown
import com.example.handndnotes.R
import com.example.handndnotes.components.HandyComponent
import com.example.handndnotes.dndspells.Common.DndSchools
import com.example.handndnotes.text.BasicTextInfo
import com.example.handndnotes.text.LabelAndText
import com.example.handndnotes.util.HandyIcon
import com.example.handndnotes.util.HandySpacer
import com.example.handndnotes.util.StringExtensions.prettyName
import com.example.handndnotes.util.toJson
import kotlinx.serialization.Serializable

fun nyi(): Nothing = throw NotImplementedError()

@Serializable
data class DndSpell(
    val name: String,
    val spellLevel: String,
    val description: String,
    val school: DndSchools,
    val upcastDescription: String? = null,
    val castingTime: String,
    val range: String,
    val components: String? = null,
    val duration: String? = null,
    val icon: Int? = null,

) : HandyComponent(name) {


    val labels = listOf(
        ::spellLevel,
        ::castingTime,
        ::range,
        ::components,
        ::duration
    ).filter { it.get() != null }

    @Composable
    override fun Content() {
        Dropdown(
            header = {
                BasicTextInfo { name }
                Spacer(Modifier.weight(1f))
                HandyIcon(R.drawable.placeholder)
            },
            dropdownModifier = Modifier.background(school.bg)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                val textColor = school.textColor
                BasicTextInfo(textColor = textColor) { "$spellLevel-level $school" }
                HandySpacer()

                labels.forEach {
                    val value = it.get() ?: return@forEach
                    LabelAndText(it.name.prettyName(), value, textColor = textColor)
                }

                HandySpacer()
                BasicTextInfo(textColor = textColor) { description }
                HandySpacer()
                upcastDescription?.let { upcast ->
                    LabelAndText("At higher Levels", upcast, textColor = textColor, separator = ".")
                }
            }
        }
    }

    override fun export(): String = toJson()
}

