package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
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
    public final RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject;

    public ProjectileSpell(int uses, int manaDrain, float castDelay, float rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject) {
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
        projectile.setSpellProperties(spellGroup.getSpellProperties());
        projectile.setOwner(entity);
        projectile.shoot(rotation.x, rotation.y, rotation.z, this.speed, this.Spread);
        world.addFreshEntity(projectile);

    }

    public void ExecuteOnHit(MagicProjectile magicProjectile, RayTraceResult rayTraceResult){
        if (rayTraceResult instanceof BlockRayTraceResult){
            magicProjectile.remove();
        }
        if (rayTraceResult instanceof EntityRayTraceResult){
            Entity entity = ((EntityRayTraceResult) rayTraceResult).getEntity();
            Entity owner = magicProjectile.getOwner();
            DamageSource damageSource;
            if (owner == null) {
                damageSource = DamageSource.indirectMagic(magicProjectile,null);
            } else {
                damageSource = DamageSource.indirectMagic(magicProjectile, owner);
                if (owner instanceof LivingEntity) {
                    ((LivingEntity)owner).setLastHurtMob(entity);
                }
            }

            if (entity.hurt(damageSource, magicProjectile.spellGroup.getSpellProperties().damageBonus + this.damage)) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingentity = (LivingEntity)entity;

                        Vector3d vector3d = magicProjectile.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale( 0.3D * 0.6D);
                        if (vector3d.lengthSqr() > 0.0D) {
                            livingentity.push(vector3d.x, 0.2D * vector3d.y, vector3d.z);
                        }
                    /* for when i want to make this pierce
                    if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
                        this.piercedAndKilledEntities.add(livingentity);
                    }
                     */

                }
                /*
                this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                if (this.getPierceLevel() <= 0) {
                    this.remove();
                }

                 */
            }
        }
    }

    public void ExecuteOnProjectileTickUnshared(MagicProjectile projectile) {
        // same as executeOnProjectileTick but only applied to the projectile of the spell, not the spell group
    }

}

