package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

import javax.annotation.Nullable;

public class PayloadSpell extends ProjectileSpell{
    public @Nullable SpellGroup payload;
    public final int count;

    public PayloadSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<EntityType<MagicProjectile>> projectileTemplate, int count) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectileTemplate);
        this.count = count;
    }

    public void CastPayload(MagicProjectile projectile){
        if (payload != null) {
            payload.Cast(projectile.getOwner(), projectile.getCommandSenderWorld(), projectile.position(), projectile.getLookAngle());
        }
    }

    @Override
    public String toString() {
        if (hasPayload()){
            return "PayloadSpell{" +
                    "payload =" + hasPayload() +
                    ", count=" + count +
                    '}';
        } else {
            return "PayloadSpell{" +
                    "payload=" + " null" +
                    ", count=" + count +
                    '}';
        }
    }

    @Override
    public boolean hasPayload() {
        return (payload != null && !payload.Spells.isEmpty());
    }
}
