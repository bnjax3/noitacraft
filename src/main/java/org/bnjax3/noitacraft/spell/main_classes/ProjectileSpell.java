package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

import java.util.Random;

public class ProjectileSpell extends Spell {

    public final RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject;
    public final ProjectileProperties projectileProperties;
    public ProjectileSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties) {
        super();
        this.projectileRegistryObject = projectileRegistryObject;
        this.projectileProperties = projectileProperties;
    }
    public ProjectileSpell(int manaDrain, RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(manaDrain);
        this.projectileRegistryObject = projectileRegistryObject;
        this.projectileProperties = projectileProperties;
    }

    @Override
    public boolean CountsToCast() {
        return true;
    }

    @Override
    public void ExecuteOnCast(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation) {
        Shoot(spellGroup, entity, world, position, rotation);

    }

    public void Shoot(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation){
        if (!world.isClientSide) {
            MagicProjectile projectile = new MagicProjectile(projectileRegistryObject.get(), position, world, this);
            projectile.setSpellGroup(spellGroup);
            projectile.setOwner(entity);

            Vector3d spreadedRot = applySpread(rotation);
            projectile.shoot(spreadedRot.x, spreadedRot.y, spreadedRot.z, this.getSpeed(), 0);
            world.addFreshEntity(projectile);
        }
    }

    public Vector3d applySpread(Vector3d rotation){
        float positiveSpread = Math.max(this.getSpread(), 0);
        Random random = new Random();
        // no se si deberia usar nextDouble o nextGaussian pero creo q queda mejor este
        double rdx = random.nextGaussian() * 2 - 1;
        double rdy = random.nextGaussian() * 2 - 1;
        double rdz = random.nextGaussian() * 2 - 1;
        Vector3d rotation2 =  new Vector3d(
                rotation.x  + rdx * positiveSpread,
                rotation.y  + rdy * positiveSpread,
                rotation.z  + rdz * positiveSpread
        );
        rotation2.normalize();
        return rotation2;
    }



    public void ExecuteOnHit(MagicProjectile magicProjectile, RayTraceResult rayTraceResult){
        if (rayTraceResult instanceof BlockRayTraceResult){
            magicProjectile.remove();
        }
    }

    public void ExecuteOnProjectileTickUnshared(MagicProjectile projectile) {
        // same as executeOnProjectileTick but only applied to the projectile of the spell, not the spell group
    }

    public float getSpeed() {
        return this.projectileProperties.getSpeed();
    }

    public Spell setSpeed(float speed) {
        this.projectileProperties.setSpeed(speed);
        return this;
    }

    public float getCritChance() {
        return this.projectileProperties.getCritChance();
    }

    public Spell setCritChance(float critChance) {
        this.projectileProperties.setCritChance(critChance);
        return this;
    }

    public float getDamage() {
        return this.projectileProperties.getDamage();
    }

    public Spell setDamage(float damage) {
        this.projectileProperties.setDamage(damage);
        return this;
    }

    public int getProjectileLifetime() {
        return this.projectileProperties.getLifetime();
    }

    public Spell setProjectileLifetime(int lifetime) {
        this.projectileProperties.setLifetime(lifetime);
        return this;
    }

    public boolean isFriendlyFire() {
        return this.projectileProperties.isFriendlyFire();
    }

    public Spell setFriendlyFire(boolean friendlyFire) {
        this.projectileProperties.setFriendlyFire(friendlyFire);
        return this;
    }

    public int getProjectileBounces() {
        return this.projectileProperties.getBounces();
    }

    public Spell setProjectileBounces(int bounces) {
        this.projectileProperties.setBounces(bounces);
        return this;
    }

    public float getGravity() {
        return this.projectileProperties.getGravity();
    }

    public Spell setGravity(float gravity) {
        this.projectileProperties.setGravity(gravity);
        return this;
    }

}

