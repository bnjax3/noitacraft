package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class LoadedTriggerSpell extends TriggerSpell {
    public final SpellGroup payload;

    public LoadedTriggerSpell(TriggerSpell spell, SpellGroup payload) {
        super(spell.projectileRegistryObject, spell.projectileProperties, spell.count);
        this.payload = payload;
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
    public boolean hasPayload() {
        // System.out.println(payload);
        return (payload != null  && !payload.isEmpty());
    }

    public void CastPayload(MagicProjectile projectile){
        if (payload == null) {
            System.out.println("Payload is null");
            return;
        }
        payload.Cast(projectile.getOwner(), projectile.getCommandSenderWorld(), projectile.position(), projectile.getDeltaMovement());
    }

    @Override
    public String toString() {
        return "LoadedTimerSpell: { " +
                "PAYLOAD: "+ this.payload +
                "}";
    }
}
