package com.example.handndnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.handndnotes.dndspells.FirstLevelSpells

import com.example.handndnotes.ui.theme.HanDnDNotesTheme
import com.example.handndnotes.util.Outline


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HanDnDNotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(Modifier.padding(innerPadding)) {
                        item {
                            MainMenu()

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainMenu() {
    Box(Modifier.smolPadding())
}

fun Modifier.smolPadding() = padding(4.dp)



@Composable
fun Dropdown(header: @Composable RowScope.() -> Unit, dropdown: @Composable () -> Unit) {
    Column {
        var expanded by remember { mutableStateOf(false) }
        Outline {
            Row(Modifier.clickable { expanded = !expanded }) {
                header()
            }
        }
        AnimatedVisibility(expanded) {
            Outline {
                dropdown()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HanDnDNotesTheme {
        LazyColumn {
            item {
                FirstLevelSpells.inflictWounds.Content()
            }


        }
    }
}
