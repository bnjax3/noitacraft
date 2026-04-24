package org.bnjax3.noitacraft.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.entity.projectiles.*;
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

    public static final RegistryObject<EntityType<BouncyBallProjectile>> BOUNCING_BURST_PROJECTILE = ENTITY_TYPES.register("bouncing_burst", () -> EntityType.Builder
            .<BouncyBallProjectile>of(BouncyBallProjectile::new, EntityClassification.MISC)
            .sized(0.4f,0.4f)
            .build("bouncing_burst"));

    public static final RegistryObject<EntityType<BubbleProjectile>> BUBBLE_PROJECTILE = ENTITY_TYPES.register("bubble", () -> EntityType.Builder
            .<BubbleProjectile>of(BubbleProjectile::new, EntityClassification.MISC)
            .sized(0.4f, 0.4f)
            .build("bubble"));

    public static final RegistryObject<EntityType<ChainsawProjectile>> CHAINSAW_PROJECTILE = ENTITY_TYPES.register("chainsaw", () -> EntityType.Builder
            .<ChainsawProjectile>of(ChainsawProjectile::new, EntityClassification.MISC)
            .sized(0.5f, 0.5f)
            .build("chainsaw"));

    public static final RegistryObject<EntityType<DiscProjectile>> DISC_PROJECTILE = ENTITY_TYPES.register("disc", () -> EntityType.Builder
            .<DiscProjectile>of(DiscProjectile::new, EntityClassification.MISC)
            .sized(0.3f, 0.6f)
            .build("disc"));

    public static final RegistryObject<EntityType<SawbladeProjectile>> SAWBLADE_PROJECTILE = ENTITY_TYPES.register("sawblade", () -> EntityType.Builder
            .<SawbladeProjectile>of(SawbladeProjectile::new, EntityClassification.MISC)
            .sized(0.3f, 1f)
            .build("sawblade"));

    public static final RegistryObject<EntityType<SpitterProjectile>> SPITTER_PROJECTILE = ENTITY_TYPES.register("spitter", () -> EntityType.Builder
            .<SpitterProjectile>of(SpitterProjectile::new, EntityClassification.MISC)
            .sized(0.4f, 0.4f)
            .build("spitter"));

    public static final RegistryObject<EntityType<EggProjectile>> EGG_PROJECTILE = ENTITY_TYPES.register("egg_projectile", () -> EntityType.Builder
            .<EggProjectile>of(EggProjectile::new, EntityClassification.MISC)
            .sized(0.4f, 0.5f)
            .build("egg_projectile"));




    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
