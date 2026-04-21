package org.bnjax3.noitacraft.spell.spells.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileProperties;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class Sawblade extends ProjectileSpell {
    public Sawblade(RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(projectileRegistryObject, projectileProperties);
    }

    public Sawblade(int manaDrain, RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(manaDrain, projectileRegistryObject, projectileProperties);
    }
}
