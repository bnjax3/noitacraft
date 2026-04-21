package org.bnjax3.noitacraft.spell.spells.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileProperties;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.main_classes.TriggerSpell;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class EggProjectile extends TriggerSpell {
    public EggProjectile(RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties, int count) {
        super(projectileRegistryObject, projectileProperties, count);
    }

    public EggProjectile(RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(projectileRegistryObject, projectileProperties);
    }

    public EggProjectile(ProjectileSpell spell, int count) {
        super(spell, count);
    }

    public EggProjectile(ProjectileSpell spell) {
        super(spell);
    }
}
