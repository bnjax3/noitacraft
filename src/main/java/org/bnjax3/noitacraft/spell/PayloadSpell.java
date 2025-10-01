package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class PayloadSpell extends ProjectileSpell{
    public final SpellGroup payload;
    public final int count;

    public PayloadSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<EntityType<MagicProjectile>> projectileTemplate, SpellGroup payload, int count) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectileTemplate);
        this.payload = payload;
        this.count = count;
    }


    @Override
    public void ExecuteOnHit(PlayerEntity player, World world, MagicProjectile magicProjectile) {
        payload.Cast(player, world, magicProjectile.getPosition(1), magicProjectile.getDeltaMovement());
    }
}
