package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

import javax.annotation.Nullable;

public class PayloadSpell extends ProjectileSpell{
    public @Nullable SpellGroup payload;
    public final int count;

    public PayloadSpell(int uses, int manaDrain, float castDelay, float rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage,
                        int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<EntityType<MagicProjectile>> projectileTemplate, int count) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectileTemplate);
        this.count = count;
    }
    public PayloadSpell(PayloadSpell spell, @Nullable SpellGroup payload){
        super(spell.Uses, spell.ManaDrain, spell.CastDelay, spell.RechargeTime, spell.Spread, spell.Recoil, spell.radius, spell.speed, spell.critChanceBonus, spell.damage,
                spell.lifetime, spell.friendlyFire, spell.bounces, spell.gravity, spell.projectileRegistryObject);
        this.count = spell.count;
        this.payload = payload;
    }



    public void CastPayload(MagicProjectile projectile){
        if (payload != null) {
            payload.Cast(projectile.getOwner(), projectile.getCommandSenderWorld(), projectile.position(), projectile.getLookAngle());
        }
    }

    @Override
    public String toString() {
            return "PayloadSpell "  + super.toString() + " {" +
                    "payload=" + payload +
                    ", count=" + count +
                    '}';

    }

    @Override
    public boolean hasPayload() {
        // System.out.println(payload);
        return (payload != null  && !payload.isEmpty());
    }
}
