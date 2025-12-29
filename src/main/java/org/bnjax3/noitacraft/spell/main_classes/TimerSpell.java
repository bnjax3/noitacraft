package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

import java.sql.Time;

public class TimerSpell extends PayloadSpell {
    private int timerLifetime = 100; // ticks
    private int timer;

    public TimerSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties, int count, int timerLifetime){
        super(projectileRegistryObject, projectileProperties, count);
        this.timerLifetime = timerLifetime;
    }
    public TimerSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties, int timerLifetime) {
        this(projectileRegistryObject, projectileProperties, 1, timerLifetime);
    }
    public TimerSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties) {
        this(projectileRegistryObject, projectileProperties, 1, projectileProperties.getLifetime());
    }
    public TimerSpell(ProjectileSpell spell){
        this(spell.projectileRegistryObject, spell.projectileProperties);
    }
    public TimerSpell(ProjectileSpell spell, int timerLifetime){
        this(spell.projectileRegistryObject, spell.projectileProperties, timerLifetime);
    }
    public TimerSpell(TimerSpell spell, SpellGroup payload){
        this(spell.projectileRegistryObject, spell.projectileProperties, spell.count, spell.timerLifetime);
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

    public int getTimerLifetime() {
        return timerLifetime;
    }

    public TimerSpell setTimerLifetime(int timerLifetime) {
        this.timerLifetime = timerLifetime;
        return this;
    }

    public int getTimer() {
        return timer;
    }
}
