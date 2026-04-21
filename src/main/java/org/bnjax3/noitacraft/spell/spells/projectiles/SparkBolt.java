package org.bnjax3.noitacraft.spell.spells.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.entity.projectiles.SparkBoltProjectile;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileProperties;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class SparkBolt extends ProjectileSpell {
    public SparkBolt(RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(projectileRegistryObject, projectileProperties);
    }

    public SparkBolt(int manaDrain, RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(manaDrain, projectileRegistryObject, projectileProperties);
    }

    public SparkBolt setDeathParticle(){
        return this;
    }

    @Override
    public void Shoot(SpellGroup spellGroup, Entity owner, World world, Vector3d position, Vector3d rotation){
        SparkBoltProjectile projectile = new SparkBoltProjectile((EntityType<SparkBoltProjectile>) projectileRegistryObject.get(), position, world, this);
        projectile.setSpellGroup(spellGroup);
        projectile.setOwner(owner);

        Vector3d spreadedRot = applySpread(rotation, spellGroup.getSpellProperties().getSpread() + this.getSpread());
        System.out.println(this.getSpread() + spellGroup.getSpellProperties().getSpread());  // 14.0f
        System.out.println(spreadedRot);
        projectile.shoot(spreadedRot.x, spreadedRot.y, spreadedRot.z, this.getSpeed() * spellGroup.getSpellProperties().getSpeedMult(), 0);
        world.addFreshEntity(projectile);
    }



    @Override
    public void ExecuteOnDeath(PlayerEntity owner, World level, MagicProjectile magicProjectile) {
        double x = magicProjectile.getX();
        double y = magicProjectile.getY();
        double z = magicProjectile.getZ();
        for(int i = 0; i < 4; ++i) {
            level.addParticle(ParticleTypes.CRIT, x, y, z, -x, -y + 0.2D, -z);
        }
        super.ExecuteOnDeath(owner, level, magicProjectile);
    }
}
