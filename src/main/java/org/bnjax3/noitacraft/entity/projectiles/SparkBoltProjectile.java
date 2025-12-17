package org.bnjax3.noitacraft.entity.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class SparkBoltProjectile extends MagicProjectile {
    public SparkBoltProjectile(EntityType<? extends ThrowableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        // add some particles later
        super.tick();
    }
}
