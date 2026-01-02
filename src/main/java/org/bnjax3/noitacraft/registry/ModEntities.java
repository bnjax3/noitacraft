package org.bnjax3.noitacraft.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.entity.projectiles.SparkBoltProjectile;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Noitacraft.MOD_ID);



    // ------------------------------------------------- PROJECTILES ----------------------------------------------------------

    public static final RegistryObject<EntityType<SparkBoltProjectile>> SPARK_BOLT_PROJECTILE = ENTITY_TYPES.register("spark_bolt", () -> EntityType.Builder
            .<SparkBoltProjectile>of(SparkBoltProjectile::new, EntityClassification.MISC)
            .sized(0.5f,0.5f)
            .build("spark_bolt"));
    /*
    ENTITY_TYPES.register("spark_bolt",() -> EntityType.Builder.
            <SparkBoltProjectile>of(SparkBoltProjectile::new, EntityClassification.MISC)
            .setCustomClientFactory((entityType, world) -> new SparkBoltProjectile(entityType, world))
            .sized(0.5f,0.5f)
            .build("spark_bolt"));
     */

    public static final RegistryObject<EntityType<MagicProjectile>> BOUNCING_BURST_PROJECTILE = ENTITY_TYPES.register("bouncing_burst",() -> EntityType.Builder.
            <MagicProjectile>of(MagicProjectile::new, EntityClassification.MISC).sized(0.5f,0.5f).build("bouncing_burst"));



    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
