package org.bnjax3.noitacraft.spell.spells.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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

    @Override
    public void Shoot(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation) {
        SparkBoltProjectile projectile = new SparkBoltProjectile((EntityType<SparkBoltProjectile>) projectileRegistryObject.get(), position, world, this);
        projectile.setSpellGroup(spellGroup);
        projectile.setOwner(entity);
        projectile.shoot(rotation.x, rotation.y, rotation.z,
                this.getSpeed() //* spellGroup.getSpellProperties().getSpeedMult()
                ,
                this.getSpread() + spellGroup.getSpellProperties().getSpread());
        world.addFreshEntity(projectile);
    }
}
