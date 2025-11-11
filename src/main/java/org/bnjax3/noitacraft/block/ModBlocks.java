package org.bnjax3.noitacraft.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.block.blocks.LitAirBlock;
import org.bnjax3.noitacraft.block.blocks.LitCaveAirBlock;
import org.bnjax3.noitacraft.block.blocks.LitWaterBlock;
import org.bnjax3.noitacraft.block.blocks.WandAltarBlock;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.item.ModItems;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Noitacraft.MOD_ID);
    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block", () -> new Block(AbstractBlock.Properties.of(Material.PISTON).harvestTool(ToolType.PICKAXE).strength(2f).harvestLevel(1)));

    public static final RegistryObject<Block> WAND_ALTAR = registerBlock("wand_altar", () -> new WandAltarBlock(AbstractBlock.Properties.of(Material.HEAVY_METAL).harvestTool(ToolType.PICKAXE).strength(20f).harvestLevel(2)));

    /*
    // for dynamic lighting aaaaaaaaaaa
    public static final RegistryObject<Block> LIT_AIR_BLOCK = BLOCKS.register("lit_air", () -> new LitAirBlock(AbstractBlock.Properties.of(Material.AIR).air().lightLevel(litBlockEmission())));

    public static final RegistryObject<Block> LIT_CAVE_AIR_BLOCK = BLOCKS.register("lit_cave_air", () -> new LitCaveAirBlock(AbstractBlock.Properties.of(Material.AIR).air().lightLevel(litBlockEmission())));

    public static final RegistryObject<Block> LIT_WATER_BLOCK = BLOCKS.register("lit_water", () -> new LitWaterBlock(Fluids.WATER, AbstractBlock.Properties.of(Material.WATER).noCollission().lightLevel(litBlockEmission())));

    private static ToIntFunction<BlockState> litBlockEmission() {
        return (blockState) -> blockState.getValue(BlockStateProperties.POWER);
    }
    */

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register( name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.NOITACRAFT_GROUP)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}