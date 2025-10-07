package org.bnjax3.noitacraft.container.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class WandAltarContainer extends Container {
    private final TileEntity tileEntity;
    private final PlayerEntity player;
    private final IItemHandler playerInventory;



    public WandAltarContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(type, windowId);
        this.tileEntity = world.getBlockEntity(pos);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);

    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return false;
    }
}
