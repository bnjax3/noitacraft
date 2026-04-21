package org.bnjax3.noitacraft.entity.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class SpitterProjectile extends MagicProjectile {
    public SpitterProjectile(EntityType<SpitterProjectile> entityType, World world) {
        super(entityType,world);
    }
    public SpitterProjectile(EntityType<SpitterProjectile> entityType, World world, ProjectileSpell spell) {
        this(entityType, world);
        Spell = spell;
        // para que no se use el metodo de la super que es una cagada
    }

    public SpitterProjectile(EntityType<SpitterProjectile> entityType, double x, double y, double z, World world, ProjectileSpell spell) {
        this(entityType, world, spell);
        this.setPos(x,y,z);
    }
    public SpitterProjectile(EntityType<SpitterProjectile> entityType, Vector3d vector3d, World world, ProjectileSpell spell) {
        this(entityType, world, spell);
        this.setPos(vector3d.x, vector3d.y, vector3d.z);
    }

    public SpitterProjectile(EntityType<SpitterProjectile> entityType, LivingEntity shooter, World world, ProjectileSpell spell) {
        this(entityType,shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ(), world, spell);
        this.setOwner(shooter);
    }
}
