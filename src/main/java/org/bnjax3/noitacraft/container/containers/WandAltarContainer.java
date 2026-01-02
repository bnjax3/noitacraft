package org.bnjax3.noitacraft.container.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.bnjax3.noitacraft.registry.ModContainers;
import org.bnjax3.noitacraft.other.Utils;

@SuppressWarnings("all")
public class WandAltarContainer extends Container {
    private final TileEntity tileEntity;
    private final PlayerEntity player;
    private final IItemHandler playerInventory;
    private final int USPS = Utils.UNIVERSAL_SLOT_PIXEL_SEPARATION; // podria poner 18 nomas pero esta mas copado esto (tambien para q utils no paresca tan inutil)
    private final Vector3d v3dPos;

    public WandAltarContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ModContainers.WAND_ALTAR_CONTAINER.get(), windowId);
        this.tileEntity = world.getBlockEntity(pos);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.v3dPos = new Vector3d(pos.getX(), pos.getY(), pos.getZ());

        layoutPlayerInventorySlots(8,119);
        if (tileEntity != null){
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h,0,9,19));
                addRow(h,1, 33 + USPS);
                addRow(h,10, 33 + USPS * 2);
                addRow(h,19, 33 + USPS * 3);
            });
        }
    }



    private void addRow(IItemHandler itemHandler, int index, int dy){
        for (int i = 0; i < 9; i++){
            addSlot(new SlotItemHandler(itemHandler, index + i, 8 + i * USPS, dy));
        }
    }



    // for the inventory i think
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // inv
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        topRow += 58;
        // hotbar ig
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
    public ItemStack quickMoveStack(PlayerEntity player, int slotIndex) {
        int slotAmount = slots.toArray().length;
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (slotIndex < slotAmount) {
                if (!this.moveItemStackTo(itemstack1, slotAmount, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, slotAmount, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }
    @Override
    public boolean stillValid(PlayerEntity player) {
        return Math.sqrt(player.distanceToSqr(v3dPos)) <= 5;
    }
}
