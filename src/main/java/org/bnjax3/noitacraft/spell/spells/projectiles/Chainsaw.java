package org.bnjax3.noitacraft.spell.spells.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileProperties;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class Chainsaw extends ProjectileSpell {
    public Chainsaw(RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(projectileRegistryObject, projectileProperties);
    }

    public Chainsaw(int manaDrain, RegistryObject<? extends EntityType<? extends MagicProjectile>> projectileRegistryObject, ProjectileProperties projectileProperties) {
        super(manaDrain, projectileRegistryObject, projectileProperties);
    }

    @Override
    public void Shoot(SpellGroup spellGroup, Entity owner, World world, Vector3d position, Vector3d DirVector) {

    }

    @Override
    public void Modify(SpellGroup spellGroup) {
        spellGroup.getSpellProperties().setCastDelay(0);
        super.Modify(spellGroup);
    }
}
