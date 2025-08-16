package org.bnjax3.noitacraft.spell;

public class StaticSpell extends Spell{
    public final int radius;
    public final float critChanceBonus;
    public final float damage;
    public final int lifetime; // ticks
    public final boolean friendlyFire;

    public StaticSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, int radius, float critChanceBonus, float damage, int lifetime, boolean friendlyFire) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, "static", true);
        this.radius = radius;
        this.critChanceBonus = critChanceBonus;
        this.damage = damage;
        this.lifetime = lifetime;
        this.friendlyFire = friendlyFire;
    }
}
