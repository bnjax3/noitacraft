package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class LoadedTimerSpell extends TimerSpell {
    public final SpellGroup payload;
    public boolean shot;
    public int timer;

    public LoadedTimerSpell(TimerSpell spell, SpellGroup payload) {
        super(spell.projectileRegistryObject, spell.projectileProperties, spell.count, spell.getProjectileLifetime());
        this.payload = payload;
        shot = false;
        timer = spell.timerLifetime;
    }

    @Override
    public void ExecuteOnHit(MagicProjectile magicProjectile, RayTraceResult rayTraceResult) {
        if (rayTraceResult instanceof BlockRayTraceResult){
            // fixed
            magicProjectile.bounce(((BlockRayTraceResult) rayTraceResult).getDirection());
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
        if (rayTraceResult instanceof EntityRayTraceResult){
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
    }

    @Override
    public void ExecuteOnProjectileTickUnshared(MagicProjectile projectile) {
        if (timer == 0){
            CastPayload(projectile);
        } else {
            timer--;
        }
        super.ExecuteOnProjectileTickUnshared(projectile);
    }

    @Override
    public boolean hasPayload() {
        // System.out.println(payload);
        return (payload != null  && !payload.isEmpty());
    }

    public void CastPayload(MagicProjectile projectile) {
        if (payload == null) {
            return;
        }
        if (shot){
            return;
        }
        payload.Cast(projectile.getOwner(), projectile.getCommandSenderWorld(), projectile.position(), projectile.getDeltaMovement().normalize());
        shot = true;
    }

    @Override
    public String toString() {
        return "LoadedTimerSpell: { " +
                "PAYLOAD: "+ this.payload +
                "}";
    }
}
