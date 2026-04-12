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
        if (entity.invulnerableTime > 10){ // parece que el rango va de 10 a 20???????
            if (damageSource instanceof GenericSpell){
                entity.invulnerableTime = 10;
                entity.hurtTime = 0;
                entity.hurtDuration = 0;
        }   }
    }
}