package org.bnjax3.noitacraft.spell.spells;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.bnjax3.noitacraft.spell.main_classes.ModifierSpell;
import org.bnjax3.noitacraft.spell.main_classes.SpellProperties;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class Light extends ModifierSpell {
    public final int intensity;
    BlockPos blockPosOfLastTick;
    BlockState blockStateOfLastTick;
    BlockState currentBlockState;
    public Light(int intensity, int manaDrain) {
        super(new SpellProperties().setManaDrain(manaDrain));
        this.intensity = intensity;
    }

    @Override
    public void ExecuteOnProjectileTick(MagicProjectile projectile) {
        /*
        World world = projectile.getCommandSenderWorld();
        if (!world.isClientSide){
            BlockPos blockPos = new BlockPos(projectile.getX(), projectile.getY(),projectile.getZ());
            currentBlockState = world.getBlockState(blockPos);
            //add light
            if (currentBlockState.is(Blocks.AIR)){
                world.setBlock(blockPos, ModBlocks.LIT_AIR_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.POWER,intensity),0);
            }
            if (currentBlockState.is(Blocks.CAVE_AIR)){
                world.setBlock(blockPos, ModBlocks.LIT_CAVE_AIR_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.POWER,intensity),0);
            }
            if (currentBlockState.is(Blocks.WATER)){
                world.setBlock(blockPos, ModBlocks.LIT_WATER_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.POWER,intensity),0);
            }
            // remove light from previous position
            if (blockPos != blockPosOfLastTick){
                if (blockStateOfLastTick.is(Blocks.AIR)){
                    world.setBlock(blockPos, Blocks.AIR.defaultBlockState(),0);
                }
                if (blockStateOfLastTick.is(Blocks.CAVE_AIR)){
                    world.setBlock(blockPos, Blocks.CAVE_AIR.defaultBlockState(),0);
                }
                if (blockStateOfLastTick.is(Blocks.WATER)){
                    world.setBlock(blockPos, Blocks.WATER.defaultBlockState(),0);
                }
            }
            blockPosOfLastTick = blockPos;
            blockStateOfLastTick = currentBlockState;
        }


         */
    }
}
