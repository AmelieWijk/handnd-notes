package com.example.handndnotes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.handndnotes.composeComponents.BasicButton
import com.example.handndnotes.util.fromJson

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import org.koin.ext.getFullName

@OptIn(ExperimentalSerializationApi::class)
//NOTE: var instead of val because weird serialization shenanigans...
// Otherwise they're excluded in subtype serialization
abstract class HandyComponent(
    var key: String? = null, //For eventual search functionality
    var keys: List<String>? = null, //For eventual search functionality
) {
    var tag = this::class.simpleName

    var qualifiedName: String = this::class.getFullName().split("$")[0]

    @Composable
    abstract fun Content()

    abstract fun export(): String
    @Composable
    fun ContentWithEditButton(){
        Box{
            Content()
            BasicButton("Edit", Modifier.alpha(0.5f)) {  }
        }
    }

    @Composable
    open fun EditView(){}

    companion object {
        val classField = HandyComponent::qualifiedName.name

        @OptIn(InternalSerializationApi::class)
        fun import(string: String): HandyComponent? {
            return try {
                string.fromJson()
            } catch (e: Exception) {
               e.printStackTrace()
               null
            }
        }
    }
}
