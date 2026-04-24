package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.other.Mather;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

import java.util.Random;

public abstract class ProjectileSpell extends Spell {

    private float distPlayerFactor = 0.5f;
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

    public void Shoot(SpellGroup spellGroup, Entity owner, World world, Vector3d position, Vector3d DirVector){
        if (!world.isClientSide) {
            position.add(DirVector.scale(this.distPlayerFactor));
            MagicProjectile projectile = new MagicProjectile(projectileRegistryObject.get(), position, world, this);
            projectile.setSpellGroup(spellGroup);
            projectile.setOwner(owner);

            Vector3d spreadedRot = applySpread(DirVector, spellGroup.getSpellProperties().getSpread() + this.getSpread());
            projectile.shoot(spreadedRot.x, spreadedRot.y, spreadedRot.z, this.getSpeed() * spellGroup.getSpellProperties().getSpeedMult(), 0);
            world.addFreshEntity(projectile);
        }
    };

    public Vector3d applySpread(Vector3d dirVector, float spread){
        // dir vector is guaranteed to be unit i think
        double radSpread = Math.max(spread, 0) * Mather.DegToRad;
        Random random = new Random();
        double theta = Mather.atan2(dirVector.y, dirVector.x) + (2 * random.nextDouble() - 1) * radSpread;
        double phi = Math.acos(dirVector.z) + (2 * random.nextDouble() - 1) * radSpread;
        phi = Math.min(Math.PI, phi);
        return new Vector3d(Math.sin(phi) * Math.cos(theta), Math.sin(phi) * Math.sin(theta), Math.cos(phi));
    }

    /*
        me costo tanto pensar y escribir esto que no lo quiero borrar
        // construct a vector of angles with a vector of dimensions
        Vector3d rotVector = new Vector3d(Mather.atan2(dirVector.y, dirVector.z), Mather.atan2(dirVector.x, dirVector.z), Mather.atan2(dirVector.y, dirVector.x));
        // apply the spread to the rotation vector  (so that it doesnt affect speed too)
        double nrx = rotVector.x + (2 * random.nextDouble() - 1) * radSpread;
        double nry = rotVector.y + (2 * random.nextDouble() - 1) * radSpread;
        double nrz = rotVector.z + (2 * random.nextDouble() - 1) * radSpread;
        // calculate new unit vector from the rotations
        double vx = Math.sin(nry) * Math.cos(nrz);
        double vy = Math.sin(nrx) * Math.sin(nrz);
        double vz = Math.cos(nry) * Math.cos(nrx);
        */



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

    public float getDistPlayerFactor() {
        return distPlayerFactor;
    }

    public ProjectileSpell setDistPlayerFactor(float distPlayerFactor) {
        this.distPlayerFactor = distPlayerFactor;
        return this;
    }
}

