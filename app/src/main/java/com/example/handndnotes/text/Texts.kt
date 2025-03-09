package com.example.handndnotes.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.handndnotes.composeComponents.smolPadding

import com.example.handndnotes.dimensions.SizeOption
import com.example.handndnotes.dimensions.resolveSize

//
//@Composable
//fun Header(text:String){
//
//}

@Composable
fun LabelAndText(label: String, value: String, textColor: Color = Color.Black, separator: String = ":") {
    val resolvedText by remember {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = textColor)) {
                    append("$label$separator ")
                }
                withStyle(SpanStyle(color = textColor)) {
                    append(value)
                }
            })
    }

    Text(resolvedText, Modifier.smolPadding())
}


//TODO: dsl builder for text options
@Composable
fun BasicTextInfo(size: SizeOption = SizeOption.Wrap, textColor: Color = Color.Black, text: () -> String) =
    Text(
        text = text(), modifier = Modifier
            .resolveSize(size)
            .smolPadding(),
        color = textColor

    )