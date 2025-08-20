package org.bnjax3.noitacraft.spell;

public class ModifierSpell extends Spell {
    public final float speedMult;
    public final float critChanceBonus;
    public final float damage;
    public final int lifetime; // ticks
    public final boolean friendlyFire;
    public final int bounces;
    public ModifierSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, float speedMult, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, false);
        this.speedMult = speedMult;
        this.critChanceBonus = critChanceBonus;
        this.damage = damage;
        this.lifetime = lifetime;
        this.friendlyFire = friendlyFire;
        this.bounces = bounces;
    }


}
