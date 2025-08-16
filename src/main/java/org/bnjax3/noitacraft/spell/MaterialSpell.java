package org.bnjax3.noitacraft.spell;

import net.minecraft.block.Block;

public class MaterialSpell extends Spell{
    public final Block material;
    public final boolean replace;
    public final int radius;
    public MaterialSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, Block material, boolean replace, int radius) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, "material", true);
        this.material = material;
        this.replace = replace;
        this.radius = radius;
    }
}
