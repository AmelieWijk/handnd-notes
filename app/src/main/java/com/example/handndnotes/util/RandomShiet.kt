package com.example.handndnotes.util

import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.java.KoinJavaComponent


//Lookup: better way of accessing this?

inline fun <reified T> koinGet(): T = KoinJavaComponent.getKoin().get()


fun MutableStateFlow<Boolean>.toggle() {
    value = !value
}

fun nyi(): Nothing = throw NotImplementedError()


enum class ToastLength(val androidValue: Int) {
    SHORT(Toast.LENGTH_SHORT),
    LONG(Toast.LENGTH_LONG)
}

fun showToast(message: String, length: ToastLength = ToastLength.SHORT) =
    Toast.makeText(koinGet(), message, length.androidValue).also { it.show() }

