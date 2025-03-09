package com.example.handndnotes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.handndnotes.composeComponents.smolPadding
import com.example.handndnotes.text.BasicTextInfo
import com.example.handndnotes.util.toJson
import kotlinx.serialization.Serializable

@Serializable
class HandyRow(
    val name: String? = null, private val _components: MutableList<HandyComponent> = mutableListOf()
) : HandyComponent(name) {

    val components: List<HandyComponent> = _components

    fun add(component: HandyComponent) = _components.add(component)
    fun remove(component: HandyComponent) = _components.remove(component)

    override fun export(): String = toJson()

    @Composable
    override fun Content() {
        Row(
            Modifier.fillMaxWidth().smolPadding(),
            horizontalArrangement = Arrangement.spacedBy(smolPadding)
        ) {
            if (components.isNotEmpty())
                components.forEach { it.Content() }
            else
                BasicTextInfo { "Empty container" }
        }
    }
}