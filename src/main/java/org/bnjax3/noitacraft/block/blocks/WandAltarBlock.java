package org.bnjax3.noitacraft.block.blocks;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.bnjax3.noitacraft.container.containers.WandAltarContainer;
import org.bnjax3.noitacraft.tileentity.tileentities.WandAltarTile;
import org.bnjax3.noitacraft.registry.ModTileEntities;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("all") // the lion doesnt concern himself with the small ide that barks
public class WandAltarBlock extends DirectionalBlock {

    public WandAltarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    public static final DirectionProperty FACING = DirectionalBlock.FACING;

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
            TileEntity tile = world.getBlockEntity(blockPos);
            if (!player.isCrouching()){
                if (tile instanceof WandAltarTile){
                    INamedContainerProvider containerProvider = createContainerProvider(world, blockPos);
                    NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tile.getBlockPos());
                } else {
                    throw new IllegalStateException("The container provider is not¿¿¿");
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos pos){
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.noitacraft.wand_altar");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {
                return new WandAltarContainer(i, world, pos, inventory, player);
            }
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    @ParametersAreNonnullByDefault
    public void onRemove(BlockState blockState, World world, BlockPos pos, BlockState blockState1, boolean b) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof WandAltarTile){
            InventoryHelper.dropItemStack(world,pos.getX(),pos.getY(),pos.getZ(), ((WandAltarTile) tileEntity).getWandWithSpells());
        }
        super.onRemove(blockState, world, pos, blockState1, b);
    }

    @SuppressWarnings("deprecation")
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public BlockRenderType getRenderShape(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext) {
        return this.defaultBlockState().setValue(FACING, blockItemUseContext.getNearestLookingDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING);
    }

    @SuppressWarnings("deprecation")
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }
}
