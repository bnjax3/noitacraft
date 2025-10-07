package org.bnjax3.noitacraft.tileentity.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.bnjax3.noitacraft.spell.SpellItem;
import org.bnjax3.noitacraft.tileentity.ModTileEntities;
import org.bnjax3.noitacraft.wand.WandItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class WandAltarTile extends TileEntity {


    private final ItemStackHandler itemStackHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);

    public WandAltarTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public WandAltarTile() {
        this(ModTileEntities.WAND_ALTAR_TILEENTITY.get());
    }

    private ItemStackHandler createHandler(){
        return new ItemStackHandler(28)
        {
            @Override
            public int getSlotLimit(int slot)
            {
                return 28;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0){
                    return stack.getItem() instanceof WandItem;
                } else if (slot > 0) {
                    return stack.getItem() instanceof SpellItem;
                }
                return false;
            }

            @Override
            public int getSlots() {
                return 28;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @ParametersAreNonnullByDefault
    @Override
    // READ
    public void load(BlockState blockState, CompoundNBT compoundNBT) {
        itemStackHandler.deserializeNBT(compoundNBT.getCompound("inv"));
        super.load(blockState, compoundNBT);
    }
    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    // WRITE
    public CompoundNBT save(CompoundNBT compoundNBT) {
        compoundNBT.put("inv", itemStackHandler.serializeNBT());
        return super.save(compoundNBT);
    }

    public ItemStack getWandWithSpells(){
        WandItem wandItem = (WandItem) itemStackHandler.getStackInSlot(0).getItem();
        for (int i = 0;i < wandItem.Wand1.Capacity;i++){
            SpellItem spellItem = (SpellItem) itemStackHandler.getStackInSlot(i).getItem();
            wandItem.spells[i] = spellItem.spell;
        }
        return new ItemStack(wandItem,1);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }




}