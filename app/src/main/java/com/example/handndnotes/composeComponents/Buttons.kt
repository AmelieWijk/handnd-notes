package com.example.handndnotes.composeComponents

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.handndnotes.text.BasicTextInfo

@Composable
fun BasicButton(text:String, modifier: Modifier = Modifier,  onClick: () -> Unit){
    Button(onClick = onClick, modifier = modifier) {
        BasicTextInfo { text }
    }
}