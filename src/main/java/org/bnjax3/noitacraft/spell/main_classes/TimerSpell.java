package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class TimerSpell extends PayloadSpell {
    public final int timerLifetime; // ticks
    public int timer;
    public TimerSpell(int uses, int manaDrain, float castDelay, float rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectile, int count, int timerLifetime) {

        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectile, count);
        this.timerLifetime = timerLifetime;
        timer = timerLifetime;
    }

    public TimerSpell(ProjectileSpell spell, int count, int timerLifetime) {
        this(spell.Uses, spell.ManaDrain, spell.CastDelay, spell.RechargeTime, spell.Spread, spell.Recoil, spell.radius, spell.speed, spell.critChanceBonus, spell.damage, spell.lifetime, spell.friendlyFire, spell.bounces, spell.gravity, spell.projectileRegistryObject, count, timerLifetime);
    }

    public TimerSpell(TimerSpell spell, SpellGroup payload){
        super(spell.Uses, spell.ManaDrain, spell.CastDelay, spell.RechargeTime, spell.Spread, spell.Recoil, spell.radius, spell.speed, spell.critChanceBonus, spell.damage,
                spell.lifetime, spell.friendlyFire, spell.bounces, spell.gravity, spell.projectileRegistryObject, spell.count);
        this.timerLifetime = spell.timerLifetime;
        this.payload = payload;
    }

    @Override
    public void ExecuteOnProjectileTickUnshared(MagicProjectile projectile) {
        if (timer > 0){
            timer--;
        } else {
            CastPayload(projectile);
        }
    }
}
