package org.bnjax3.noitacraft.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.block.ModBlocks;
import org.bnjax3.noitacraft.tileentity.tileentities.WandAltarTile;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Noitacraft.MOD_ID);

    public static final RegistryObject<TileEntityType<WandAltarTile>> WAND_ALTAR_TILEENTITY = TILE_ENTITIES.register("wand_altar_tileentity", () -> TileEntityType.Builder.of(WandAltarTile::new, ModBlocks.WAND_ALTAR.get()).build(null));

    public static void register(IEventBus eventBus){
        TILE_ENTITIES.register(eventBus);
    }
}






























