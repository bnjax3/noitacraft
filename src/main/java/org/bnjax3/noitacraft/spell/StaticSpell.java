package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;

public class StaticSpell extends ProjectileSpell{

    public StaticSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, float radius, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, RegistryObject<EntityType<MagicProjectile>> projectileTemplate) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, 0, critChanceBonus, damage, lifetime, friendlyFire, bounces, 0, projectileTemplate);
    }
}
