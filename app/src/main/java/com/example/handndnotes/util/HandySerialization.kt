package com.example.handndnotes.util

import com.example.handndnotes.components.HandyComponent
import kotlinx.serialization.json.Json

object HandySerialization {
    val json = Json
}

inline fun <reified T: HandyComponent> T.toJson() = HandySerialization.json.encodeToString(this)