package org.bnjax3.noitacraft.event;


import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.damageSources.GenericSpell;

@Mod.EventBusSubscriber(modid = Noitacraft.MOD_ID)
public class DamageEvents {
    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        LivingEntity entity = event.getEntityLiving();
        DamageSource damageSource = event.getSource();
        if (entity.level.isClientSide){return;}
        System.out.println("function called");
        System.out.println("Event DamageSource : " + damageSource);
        System.out.println("Event BypassInvul : " + damageSource.isBypassInvul());
        if (entity.invulnerableTime > 10){ // parece que el rango va de 10 a 20???????
            if (damageSource instanceof GenericSpell){
                entity.invulnerableTime = 10;
                entity.hurtTime = 0;
                entity.hurtDuration = 0;
                System.out.println("\nIframes Bypassed\n");
            } else {
                System.out.println("tried to hit during iframes with a damage type without bypass");
            }
        }
        String instance = "CLIENT";
        if (!entity.level.isClientSide){ instance = "SERVER";}
        System.out.println("DETAILS: { \n" +
                instance + "\n" +
                "entity: " + entity + "\n" +
                "IframesLeft: " + entity.invulnerableTime + "\n" +
                "DamageSource: " + damageSource + "\n" +
                "Instance of GenericSpell: " + (damageSource instanceof GenericSpell) + "\n" +
                "}");
    }
}