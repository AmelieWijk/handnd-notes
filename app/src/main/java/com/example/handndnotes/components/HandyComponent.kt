package com.example.handndnotes.components

import android.util.Log
import androidx.compose.runtime.Composable
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.koin.ext.getFullName

@Serializable
@OptIn(ExperimentalSerializationApi::class)
abstract class HandyComponent(
    val key: String? = null, //For eventual search functionality
    val keys: List<String>? = null, //For eventual search functionality
) {
    val tag = this::class.simpleName

    @EncodeDefault
    val qualifiedName: String = this::class.getFullName().split("$")[0]

    @Composable
    abstract fun Content()
    abstract fun export(): String

    companion object {
        val tag = HandyComponent::class.simpleName
        val qualifiedNameRegex = "qualifiedName\":\"(.*?)\"".toRegex()

        @OptIn(InternalSerializationApi::class)
        //FIXME: first solution, should use some jsonParser instead of hacky regex
        fun import(string: String): HandyComponent? {
            val className = qualifiedNameRegex.find(string)?.groups[1]?.value ?: return null

            return try {
                val clazz = Class.forName(className).kotlin
                Json.decodeFromString(clazz.serializer(), string) as? HandyComponent
            } catch (e: Exception) {
                Log.e(
                    tag,
                    "Failed to import string. classname: " + "$className, full string: $string",
                    e
                )
                null
            }
        }
    }
}
