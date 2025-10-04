package org.bnjax3.noitacraft.block.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.fluids.FluidAttributes;

public class LitWaterBlock extends FlowingFluidBlock {
    public final IntegerProperty POWER = BlockStateProperties.POWER;
    public LitWaterBlock(FlowingFluid flowingFluid, Properties properties) {
        super(flowingFluid, properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(BlockStateProperties.POWER, 15));
    }
}