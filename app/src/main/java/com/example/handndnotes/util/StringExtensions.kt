package com.example.handndnotes.util

object StringExtensions {
    val prettyRegex = "([A-Z](?=[A-Z]))|([A-Z]?[a-z]+)".toRegex()

    fun String.prettyName() = prettyRegex.findAll(this)
        .joinToString(" ") {
            it.value[0].uppercase() + it.value.substring(1)
        }

}

