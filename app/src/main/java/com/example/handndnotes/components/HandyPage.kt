package com.example.handndnotes.components

import androidx.compose.runtime.Composable
import com.example.handndnotes.util.HandySerialization
import com.example.handndnotes.util.toJson
import kotlinx.serialization.Serializable

@Serializable
data class HandyPage(val name: String) : HandyComponent(name) {

    companion object {
        fun of(name: String, components: List<HandyComponent>): HandyPage =
            HandyPage(name).also {
                it.components.addAll(components)
            }
    }

    val components = mutableListOf<HandyComponent>()

    @Composable
    override fun Content() {

    }

    override fun export(): String = toJson()
}