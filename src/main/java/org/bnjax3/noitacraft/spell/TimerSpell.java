package org.bnjax3.noitacraft.spell;

public class TimerSpell extends ProjectileSpell{
    public final int timerLifetime; // ticks
    public Spell[] payload;

    public TimerSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, int radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, int timerLifetime)
    {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity);
        this.timerLifetime = timerLifetime;
    }

}
