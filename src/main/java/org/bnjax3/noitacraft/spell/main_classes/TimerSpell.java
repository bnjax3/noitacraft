package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

import java.sql.Time;

public class TimerSpell extends PayloadSpell {

    public TimerSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties, int count, int timerLifetime){
        super(projectileRegistryObject, projectileProperties.setLifetime(timerLifetime), count);

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
        this(spell.projectileRegistryObject, spell.projectileProperties, spell.count, spell.getProjectileLifetime());
        this.payload = payload;
    }

    @Override
    public void ExecuteOnDespawn(PlayerEntity owner, World level, MagicProjectile projectile){
        CastPayload(projectile);
    }


    @Override
    public void ExecuteOnHit(MagicProjectile magicProjectile, RayTraceResult rayTraceResult) {
        if (rayTraceResult instanceof BlockRayTraceResult){
            // fixed
            magicProjectile.bounce(((BlockRayTraceResult) rayTraceResult).getDirection());
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
        if (rayTraceResult instanceof EntityRayTraceResult){
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
    }
}
