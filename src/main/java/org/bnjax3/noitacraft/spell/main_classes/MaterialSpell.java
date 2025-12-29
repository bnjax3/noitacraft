package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.block.Block;

public class MaterialSpell extends Spell{
    public final Block material;
    public final boolean replace;
    public final int radius;
    public MaterialSpell(SpellProperties spellProperties, Block material, boolean replace, int radius) {
        super(spellProperties);
        this.material = material;
        this.replace = replace;
        this.radius = radius;
    }
}
