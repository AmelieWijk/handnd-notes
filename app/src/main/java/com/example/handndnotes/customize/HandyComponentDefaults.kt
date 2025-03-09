package com.example.handndnotes.customize

import com.example.handndnotes.components.HandyRow
import com.example.handndnotes.components.HandyDropdown
import com.example.handndnotes.components.HandyPage
import com.example.handndnotes.components.HandyText
import com.example.handndnotes.dndspells.FirstLevelSpells

object HandyComponentDefaults {

    val text = DefaultComponent("Text") { HandyText(initialText = "text") }
    val dropdown = DefaultComponent("Dropdown") {
        HandyDropdown().apply {
            header.add(HandyText(initialText = "Header"))
            dropdown.add(HandyText(initialText = "Dropdown"))
        }
    }
    val container = DefaultComponent("Row") {
        HandyRow().apply {
            add(HandyText(initialText = "OneItem"))
            add(dropdown.defaultProvider())
        }
    }

    //Don't include in list of components to add
    val page = DefaultComponent("Page") {
        HandyPage.of(
            "MyFirstPage", listOf(
                FirstLevelSpells.inflictWounds,
                container.defaultProvider()
            )
        )
    }
}