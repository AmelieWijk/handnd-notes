package com.example.handndnotes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.handndnotes.composeComponents.smolPadding
import com.example.handndnotes.text.BasicTextInfo
import com.example.handndnotes.util.toJson

data class HandyPage(val name: String) : HandyComponent(name) {

    companion object {
        fun of(name: String, components: List<HandyComponent>): HandyPage =
            HandyPage(name).also {
                it._components.addAll(components)
            }
    }

    var components: List<HandyComponent> = mutableListOf<HandyComponent>()
    val _components: MutableList<HandyComponent>
        get() = components as MutableList

    fun add(component: HandyComponent) = _components.add(component)
    fun remove(component: HandyComponent) = _components.remove(component)

    @Composable
    override fun Content() {
        Column(
            Modifier
                .fillMaxWidth()
                .smolPadding(),
            verticalArrangement = Arrangement.spacedBy(smolPadding)
        ) {
            if (components.isEmpty()) {
                BasicTextInfo {
                    "Currently empty, add stuff by pressing button at the top"
                }
            } else {
                components.forEach {
                    it.Content()
                }
            }
        }
    }

    override fun export(): String = toJson()
}