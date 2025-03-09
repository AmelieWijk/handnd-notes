package com.example.handndnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.handndnotes.composeComponents.BasicButton
import com.example.handndnotes.composeComponents.smolPadding
import com.example.handndnotes.customize.CustomizeModel
import com.example.handndnotes.ui.theme.HanDnDNotesTheme
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject


enum class AppViews {
    Pages, //Also contains customize mode
    ComponentPreview

}

//TODO: temp, move elsewhere
val currentView = MutableStateFlow(AppViews.Pages)

class MainActivity : ComponentActivity() {


    val pageHandler: PageHandler by inject()
    val customizeModel: CustomizeModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HanDnDNotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val currentView by currentView.collectAsState()
                    val currentPage by pageHandler.selectedPage.collectAsState()
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(smolPadding),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        when (currentView) {
                            //TODO: öh, move this
                            AppViews.Pages -> {
                                item {
                                    MainMenu()
                                }
                                currentPage.components.forEach {
                                    item {
                                        it.Content()
                                    }
                                }
                            }

                            AppViews.ComponentPreview -> {
                                item {
                                    BasicButton("<Exit>") {
                                        com.example.handndnotes.currentView.value =
                                            AppViews.Pages
                                    }
                                }
                                customizeModel.defaultComponents.forEach {
                                    item {
                                        BasicButton(it.displayName) {
                                            customizeModel.addToCurrentPage(
                                                it.defaultProvider()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun MainMenu(
        pageHandler: PageHandler = koinInject(),
        customizeVm: CustomizeModel = koinInject()
    ) {
        val customizeActive by customizeVm.customizeActive.collectAsState()
        val pages = remember { pageHandler.pages }.toList()

        LazyRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(smolPadding)
        ) {
            if (customizeActive) {
                fun btn(text: String, onClick: () -> Unit) =
                    item { BasicButton(text, onClick = onClick) }
                //Show pages below?
                btn("Add Page") { customizeVm.addPage() }
                btn("Add Component") { customizeVm.addComponent() }
                btn("Save") { customizeVm.save() }
                btn("Cancel") { customizeVm.cancel() }
            } else {
                pages.forEach {
                    item { BasicButton(it.name) { pageHandler.open(it) } }
                }
                item { BasicButton("Customize") { customizeVm.start() } }
            }
        }
    }

}


