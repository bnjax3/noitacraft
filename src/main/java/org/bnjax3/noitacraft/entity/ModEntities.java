package org.bnjax3.noitacraft.entity;

import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Noitacraft.MOD_ID);


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
