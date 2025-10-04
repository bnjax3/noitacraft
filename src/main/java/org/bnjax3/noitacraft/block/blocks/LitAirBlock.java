package org.bnjax3.noitacraft.block.blocks;



import net.minecraft.block.AirBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class LitAirBlock extends AirBlock {
    public final IntegerProperty POWER = BlockStateProperties.POWER;
    public LitAirBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(BlockStateProperties.POWER, 15));
    }


}