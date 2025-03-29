package com.example.handndnotes.util

import com.example.handndnotes.components.HandyComponent
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

val moshi = Moshi.Builder().apply {
    add(HandyComponentAdapter())
    addLast(KotlinJsonAdapterFactory())
}.build()

inline fun <reified T : HandyComponent> T.toJson() = moshi.adapter(T::class.java).toJson(this)

fun String.fromJson(): HandyComponent {
    val json = Json.parseToJsonElement(this)
    val className = json.jsonObject.entries
        .find { it.key == HandyComponent::qualifiedName.name }
        ?.value?.jsonPrimitive?.content

    val clazz = Class.forName(className)

    return moshi.adapter(clazz).fromJson(this) as HandyComponent
}

@Suppress("unused") //Most definitely used during runtime
class HandyComponentAdapter {

    @FromJson
    fun fromJson(jsonString: String): HandyComponent? {
        val json = Json.parseToJsonElement(jsonString)
        val className = json.jsonObject.entries.find { it.key == HandyComponent.classField }?.value?.jsonPrimitive?.content
        val clazz = Class.forName(className)

        return moshi.adapter(clazz).fromJson(jsonString) as? HandyComponent
    }

    @ToJson
    fun toJson(component: HandyComponent):String = component.export()

}
