package org.bnjax3.noitacraft.spell;

import org.bnjax3.noitacraft.wand.SpellGroup;

public class TimerSpell extends PayloadSpell{
    public final int timerLifetime; // ticks


    public TimerSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, int radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, MagicProjectile projectile, SpellGroup payload, int count, int timerLifetime) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectile, payload, count);
        this.timerLifetime = timerLifetime;
    }

    public TimerSpell(TimerSpell spell, SpellGroup payload) {
        this(spell.Uses, spell.ManaDrain, spell.CastDelay, spell.RechargeTime, spell.Spread, spell.Recoil, spell.radius, spell.speed, spell.critChanceBonus, spell.damage, spell.lifetime, spell.friendlyFire, spell.bounces, spell.gravity, spell.projectile, payload, spell.count, spell.timerLifetime);
    }
}
