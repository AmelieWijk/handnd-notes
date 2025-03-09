package com.example.handndnotes.components

import androidx.compose.runtime.Composable
import com.example.handndnotes.composeComponents.Dropdown

import com.example.handndnotes.util.toJson
import kotlinx.serialization.Serializable

@Serializable
class HandyDropdown(val name: String? = null) : HandyComponent(name) {

    val header = HandyRow()
    val dropdown = HandyRow()

    @Composable
    override fun Content() {
        Dropdown({ header.Content() }) {
            dropdown.Content()
        }
    }

    override fun export(): String = toJson()
}


