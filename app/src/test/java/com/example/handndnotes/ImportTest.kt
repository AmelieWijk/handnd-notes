package com.example.handndnotes

import com.example.handndnotes.components.HandyComponent
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
    fun testExportImport() {
        val exported = FirstLevelSpells.inflictWounds.export()
        assert(exported.isNotEmpty())

        val imported = HandyComponent.import(exported)
        assertNotNull(imported)
        assert(imported == FirstLevelSpells.inflictWounds)
    }
}