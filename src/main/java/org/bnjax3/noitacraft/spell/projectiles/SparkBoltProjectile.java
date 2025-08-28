package org.bnjax3.noitacraft.spell.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.MagicProjectile;
import org.bnjax3.noitacraft.spell.ProjectileSpell;
import org.bnjax3.noitacraft.wand.SpellGroup;

import javax.annotation.Nullable;

public class SparkBoltProjectile extends MagicProjectile {


    public SparkBoltProjectile(EntityType<? extends ThrowableEntity> entityType, World world, ProjectileSpell spell) {
        super(entityType, world, spell);
    }

    public SparkBoltProjectile(EntityType<SparkBoltProjectile> projectileEntityType, World world) {
        super(projectileEntityType, world);
    }
}
