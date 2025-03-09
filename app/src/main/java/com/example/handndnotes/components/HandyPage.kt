package com.example.handndnotes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.handndnotes.composeComponents.smolPadding
import com.example.handndnotes.text.BasicTextInfo
import com.example.handndnotes.util.HandySnapshotList
import com.example.handndnotes.util.SnapshotListSerializer
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

    val components: HandySnapshotList<HandyComponent> = HandySnapshotList<HandyComponent>()

    fun add(component: HandyComponent) = components.add(component)
    fun remove(component: HandyComponent) = components.remove(component)

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