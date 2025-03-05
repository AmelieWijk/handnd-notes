package com.example.handndnotes.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

import com.example.handndnotes.dimensions.SizeOption
import com.example.handndnotes.dimensions.resolveSize
import com.example.handndnotes.smolPadding
//
//@Composable
//fun Header(text:String){
//
//}

@Composable
fun LabelAndText(label: String, value: String, boldedSeparator: String = ":") {
    val resolvedText by remember {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label$boldedSeparator ")
                }
                append(value)
            })
    }

    Text(resolvedText)
}


@Composable
fun BasicTextInfo(size: SizeOption = SizeOption.Wrap, text: () -> String) =
    Text(
        text(), Modifier
            .resolveSize(size)
            .smolPadding()
    )