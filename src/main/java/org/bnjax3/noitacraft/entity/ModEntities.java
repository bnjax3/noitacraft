package org.bnjax3.noitacraft.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Noitacraft.MOD_ID);



    // ------------------------------------------------- PROJECTILES ----------------------------------------------------------

    public static final RegistryObject<EntityType<MagicProjectile>> SPARK_BOLT_PROJECTILE = ENTITY_TYPES.register("spark_bolt",() -> EntityType.Builder.<MagicProjectile>of(MagicProjectile::new, EntityClassification.MISC).sized(0.5f,0.5f).build("spark_bolt"));





    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
