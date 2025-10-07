package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class PayloadSpell extends ProjectileSpell{
    public SpellGroup payload;
    public final int count;

    public PayloadSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<EntityType<MagicProjectile>> projectileTemplate, int count) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectileTemplate);
        this.count = count;
    }

    public void CastPayload(MagicProjectile projectile){
        payload.Cast(projectile.getOwner(),projectile.getCommandSenderWorld(),projectile.position(),projectile.getLookAngle());
    }


}
