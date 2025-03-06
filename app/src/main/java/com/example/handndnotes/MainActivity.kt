package com.example.handndnotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.handndnotes.dndspells.FirstLevelSpells
import com.example.handndnotes.extensions.handyBorder
import com.example.handndnotes.smolPadding

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

val smolPadding = 4.dp
fun Modifier.smolPadding() = padding(smolPadding)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HanDnDNotesTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(smolPadding),
            modifier = Modifier.smolPadding()
        ) {
            repeat(50) {
                item {
                    FirstLevelSpells.inflictWounds.Content()
                }
            }
        }
    }
}

@Composable
@SuppressLint("ModifierParameter")
fun Dropdown(
    header: @Composable RowScope.() -> Unit,
    headerModifier: Modifier = Modifier,
    dropdownModifier: Modifier = Modifier,
    dropdown: @Composable () -> Unit
) {
    Column {
        var expanded by remember { mutableStateOf(false) }
        Outline {
            Row(headerModifier.clickable { expanded = !expanded }) {
                header()
            }
        }
        AnimatedVisibility(expanded) {
            Box(
                Modifier
                    .padding(start = smolPadding, end = smolPadding, bottom = smolPadding)
                    .then(dropdownModifier)
                    .handyBorder {
                        startAndEnd(2.dp, Color.Black)
                        bottom.apply(2.dp, Color.Black)
                    }
                    .smolPadding()
            ) {
                dropdown()
            }

        }
    }
}
