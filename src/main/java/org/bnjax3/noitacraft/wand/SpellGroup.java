package org.bnjax3.noitacraft.wand;

import org.bnjax3.noitacraft.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellGroup {
    public final Spell[] Casted;
    public final Spell[] Modifiers;
    public final Spell[] Multicasts;
    public SpellGroup(Spell[] casted, Spell[] modifiers, Spell[] multicasts) {
        Casted = casted;
        Modifiers = modifiers;
        Multicasts = multicasts;
    }
}
