package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class TriggerSpell extends PayloadSpell {

    public TriggerSpell(int uses, int manaDrain, float castDelay, float rechargeTime, float spread, float recoil, float radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, RegistryObject<EntityType<MagicProjectile>> projectileTemplate, int count) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, radius, speed, critChanceBonus, damage, lifetime, friendlyFire, bounces, gravity, projectileTemplate, count);
    }
    public TriggerSpell(ProjectileSpell spell, int count)
    {
        super(spell.Uses, spell.ManaDrain, spell.CastDelay, spell.RechargeTime, spell.Spread, spell.Recoil, spell.radius, spell.speed, spell.critChanceBonus, spell.damage, spell.lifetime, spell.friendlyFire, spell.bounces, spell.gravity, spell.projectileRegistryObject, count);
    }
    public TriggerSpell(TriggerSpell spell, SpellGroup payload){
        super(spell.Uses, spell.ManaDrain, spell.CastDelay, spell.RechargeTime, spell.Spread, spell.Recoil, spell.radius, spell.speed, spell.critChanceBonus, spell.damage,
                spell.lifetime, spell.friendlyFire, spell.bounces, spell.gravity, spell.projectileRegistryObject, spell.count);
        this.payload = spell.payload;
    }
    @Override
    public void ExecuteOnHit(MagicProjectile magicProjectile, RayTraceResult rayTraceResult) {
        if (rayTraceResult instanceof BlockRayTraceResult){
            magicProjectile.bounce(((BlockRayTraceResult) rayTraceResult).getDirection());
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
        if (rayTraceResult instanceof EntityRayTraceResult){
            ((EntityRayTraceResult) rayTraceResult).getEntity().hurt(DamageSource.GENERIC, damage);
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
    }
}
