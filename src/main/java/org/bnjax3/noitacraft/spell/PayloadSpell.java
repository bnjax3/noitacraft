package org.bnjax3.noitacraft.spell;

import org.bnjax3.noitacraft.wand.SpellGroup;

public class PayloadSpell extends ProjectileSpell{
    public final SpellGroup payload;
    public final int count;
    public PayloadSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, int radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, SpellGroup payload, int count)
    {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity);
        this.payload = payload;
        this.count = count;
    }
}
