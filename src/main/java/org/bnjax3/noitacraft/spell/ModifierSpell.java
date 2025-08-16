package org.bnjax3.noitacraft.spell;

public class ModifierSpell extends Spell{
    public ModifierSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, "modifier", false);
    }
}
