package com.example.handndnotes

import com.example.handndnotes.components.HandyComponent
import com.example.handndnotes.components.HandyPage
import com.example.handndnotes.customize.HandyComponentDefaults
import com.example.handndnotes.dndspells.FirstLevelSpells
import com.example.handndnotes.dndspells.model.DndSpell
import kotlinx.serialization.InternalSerializationApi
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ImportTest {
    @OptIn(InternalSerializationApi::class)
    @Test
    fun testExportImport_Dropdown() {
        val input = FirstLevelSpells.inflictWounds
        val imported = exportImport(input) as DndSpell

        assert(imported == input)
    }

    fun exportImport(component: HandyComponent): HandyComponent?{
        val exported = component.export()
        assert(exported.isNotEmpty())
        assert(exported.contains(HandyComponent.classField)) {
            "Did not contain ${HandyComponent.classField}. Exported msg: \n$exported"
        }

        val imported = HandyComponent.import(exported)
        assertNotNull(imported)
        assert(imported == component)
        return imported
    }

    @Test
    fun testExportImport_Page(){
        val input = HandyComponentDefaults.page.defaultProvider()

        val imported = exportImport(HandyComponentDefaults.page.defaultProvider()) as HandyPage
        assert(imported.components.size == input.components.size)
        assert(imported == input)
    }

}