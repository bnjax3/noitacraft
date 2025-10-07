package org.bnjax3.noitacraft.block.blocks;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.tileentity.tileentities.WandAltarTile;
import org.bnjax3.noitacraft.tileentity.ModTileEntities;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class WandAltarBlock extends Block {

    public WandAltarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.WAND_ALTAR_TILEENTITY.get().create();
    }

    @Override
    @SuppressWarnings("deprecation")
    @ParametersAreNonnullByDefault
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (!world.isClientSide){

        }
        return super.use(blockState, world, blockPos, player, hand, blockRayTraceResult);
    }

    @Override
    @SuppressWarnings("deprecation")
    @ParametersAreNonnullByDefault
    public void onRemove(BlockState blockState, World world, BlockPos pos, BlockState blockState1, boolean b) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof WandAltarTile){
            InventoryHelper.dropItemStack(world,pos.getX(),pos.getY(),pos.getZ(), ((WandAltarTile) tileEntity).getWandWithSpells());
            super.onRemove(blockState, world, pos, blockState1, b);
        }


    }


    @SuppressWarnings("deprecation")
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public BlockRenderType getRenderShape(BlockState blockState) {
        return BlockRenderType.MODEL;
    }



}
