package com.example.handndnotes

import com.example.handndnotes.components.HandyComponent
import com.example.handndnotes.components.HandyPage
import com.example.handndnotes.customize.HandyComponentDefaults
import com.example.handndnotes.dndspells.Common.DndSchools
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
        importExport(FirstLevelSpells.inflictWounds)
    }

    fun importExport(component: HandyComponent){
        val exported = component.export()
        assert(exported.isNotEmpty())

        val imported = HandyComponent.import(exported)
        assertNotNull(imported)
        assert(imported == component)
    }

    @Test
    fun testExportImport_Page(){
        importExport(HandyComponentDefaults.page.defaultProvider())
    }

}