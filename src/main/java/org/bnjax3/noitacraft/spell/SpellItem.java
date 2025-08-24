package org.bnjax3.noitacraft.spell;

import net.minecraft.item.Item;

public class SpellItem extends Item {
    public Spell spell;
    public SpellItem(Properties p_i48487_1_, Spell spell) {
        super(p_i48487_1_);
        this.spell = spell;
    }

    
}
