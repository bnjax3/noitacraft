package org.bnjax3.noitacraft.spell;

public class TriggerSpell extends ProjectileSpell{
    public final int triggers;
    public Spell[] payload;

    public TriggerSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, int radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, int triggers) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity);
        this.triggers = triggers;
    }

    // on hit cast functionality later
}
