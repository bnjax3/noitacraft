package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class TriggerSpell extends PayloadSpell{

    public TriggerSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<EntityType<MagicProjectile>> projectileTemplate, SpellGroup payload, int count) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectileTemplate, payload, count);
    }
    public TriggerSpell(TriggerSpell spell, SpellGroup payload)
    {
        super(spell.Uses, spell.ManaDrain, spell.CastDelay, spell.RechargeTime, spell.Spread, spell.Recoil, spell.radius, spell.speed, spell.critChanceBonus, spell.damage, spell.lifetime, spell.friendlyFire, spell.bounces, spell.gravity, spell.projectileRegistryObject, payload, spell.count);
    }


    // on hit cast functionality later
}
