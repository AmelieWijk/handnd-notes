package com.example.handndnotes.customize

import com.example.handndnotes.AppViews
import com.example.handndnotes.PageHandler
import com.example.handndnotes.components.HandyComponent
import com.example.handndnotes.currentView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class DefaultComponent<T: HandyComponent>(val displayName: String, val defaultProvider: () -> T)

class CustomizeModel(val pageHandler: PageHandler) {

   val defaultComponents =
       HandyComponentDefaults.let { pre ->
           listOf<DefaultComponent<*>>(
               pre.text,
               pre.dropdown,
               pre.container
           )
       }

    private val _customizeMode = MutableStateFlow(false)
    val customizeActive: StateFlow<Boolean> = _customizeMode

    fun addToCurrentPage(component: HandyComponent) {
        pageHandler.addToCurrent(component)
        currentView.value = AppViews.Pages
    }

    fun start(){ _customizeMode.value = true}

    fun cancel(){ _customizeMode.value =false}

    fun save(){}

    fun addComponent() {
        currentView.value = AppViews.ComponentPreview
    }

    fun addPage() {}

}