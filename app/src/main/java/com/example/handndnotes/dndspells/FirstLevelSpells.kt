package com.example.handndnotes.dndspells

import com.example.handndnotes.dndspells.CommonDndSpellValues.CastingTime
import com.example.handndnotes.dndspells.CommonDndSpellValues.Duration
import com.example.handndnotes.dndspells.CommonDndSpellValues.Range
import com.example.handndnotes.dndspells.CommonDndSpellValues.SpellLevel
import com.example.handndnotes.dndspells.model.DndSpell

object FirstLevelSpells {

    val inflictWounds = DndSpell(
        name = "Inflict Wounds",
        spellLevel = SpellLevel.first,
        school = "necromancy",
        description = "Make a melee spell attack against a creature you can reach. On a hit, the target takes 3d10 necrotic damage.",
        upcastDescription = "When you cast this spell using a spell slot of 2nd level or higher, the damage increases by 1d10 for each slot level above 1st.",
        range = Range.touch,
        duration = Duration.instant,
        castingTime = CastingTime.action
    )

}