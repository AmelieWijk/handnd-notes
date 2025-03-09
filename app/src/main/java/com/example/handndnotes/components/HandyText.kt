package com.example.handndnotes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.example.handndnotes.text.BasicTextInfo
import com.example.handndnotes.util.toJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable

@Serializable
class HandyText(val name: String? = null, val initialText: String): HandyComponent(name){

    val text = MutableStateFlow<String>(initialText)

    @Composable
    override fun Content() {
        val text = text.collectAsState().value
        BasicTextInfo { text }
    }

    override fun export(): String = toJson()
}