package com.example.handndnotes

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.handndnotes.components.HandyComponent
import com.example.handndnotes.components.HandyPage
import com.example.handndnotes.customize.HandyComponentDefaults
import com.example.handndnotes.util.showToast
import kotlinx.coroutines.flow.MutableStateFlow

class PageHandler() {

    val pages: SnapshotStateList<HandyPage> = SnapshotStateList()

    val selectedPage = MutableStateFlow(HandyComponentDefaults.page.defaultProvider())

    fun loadInit() {}

    fun savePages() {}

    fun open(page: HandyPage) {
        showToast("opening page ${page.name}")
    }

    fun addToCurrent(component: HandyComponent){
        selectedPage.value.add(component)
    }

}
