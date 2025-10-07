package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class ProjectileSpell extends Spell {
    public final float radius;
    public final float speed;
    public final float critChanceBonus;
    public final float damage;
    public final int lifetime; // ticks
    public final boolean friendlyFire;
    public final int bounces;
    public final float gravity; // block/tick
    public final RegistryObject<EntityType<MagicProjectile>> projectileRegistryObject;

    public ProjectileSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<EntityType<MagicProjectile>> projectileRegistryObject) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, true);
        this.radius = radius;
        this.speed = speed;
        this.critChanceBonus = critChanceBonus;
        this.damage = damage;
        this.lifetime = lifetime;
        this.friendlyFire = friendlyFire;
        this.bounces = bounces;
        this.gravity = gravity;
        this.projectileRegistryObject = projectileRegistryObject;
    }

    @Override
    public void ExecuteOnCast(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation) {
        Shoot(spellGroup, entity, world, position, rotation);

    }

    public void Shoot(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation){
        MagicProjectile projectile = new MagicProjectile(projectileRegistryObject.get(), position, world, this);
        projectile.setSpellGroup(spellGroup);
        projectile.setSpellProperties(spellGroup.spellProperties);
        projectile.setOwner(entity);
        projectile.shoot(rotation.x, rotation.y, rotation.z, this.speed, this.Spread);

    }

    public void ExecuteOnHit(MagicProjectile magicProjectile, RayTraceResult rayTraceResult){
        if (rayTraceResult instanceof BlockRayTraceResult){
            magicProjectile.remove();
        }
        if (rayTraceResult instanceof EntityRayTraceResult){
            ((EntityRayTraceResult) rayTraceResult).getEntity().hurt(DamageSource.GENERIC, damage);
            magicProjectile.remove();
        }
    }

    public void ExecuteOnProjectileTickUnshared(MagicProjectile projectile) {
        // same as executeOnProjectileTick but only applied to the projectile of the spell, not the spell group
    }

}

