package org.bnjax3.noitacraft.tileentity.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import org.bnjax3.noitacraft.item.ModItems;
import org.bnjax3.noitacraft.spell.SpellItem;
import org.bnjax3.noitacraft.spell.main_classes.Spell;
import org.bnjax3.noitacraft.tileentity.ModTileEntities;
import org.bnjax3.noitacraft.wand.WandItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

public class WandAltarTile extends TileEntity {


    private final ItemStackHandler itemStackHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);

    public WandAltarTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public WandAltarTile() {
        this(ModTileEntities.WAND_ALTAR_TILEENTITY.get());
    }

    @Override
    public CompoundNBT serializeNBT() {
        return super.serializeNBT();
    }

    @Override
    public void deserializeNBT(BlockState state, CompoundNBT nbt) {
        super.deserializeNBT(state, nbt);
    }

    private ItemStackHandler createHandler(){
        return new ItemStackHandler(28)
        {
            @Override
            public int getSlotLimit(int slot)
            {
                return 28;
            }
            // slot 0 -> wand slot
            // slots 1-28 -> spell slots
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0){
                    return stack.getItem() instanceof WandItem;
                } else if (!itemStackHandler.getStackInSlot(0).isEmpty()) {
                    if (slot <= ((WandItem) itemStackHandler.getStackInSlot(0).getItem()).Wand1.Capacity){
                        return stack.getItem() instanceof SpellItem;
                    }
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
                if (slot == 0){
                    fillSpellSlots(((WandItem) stack.getItem()).spellItems);
                }
                return super.insertItem(slot, stack, simulate);
            }

            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot == 0){
                    Item wandItem = getWandWithSpells().getItem();
                    ((WandItem)wandItem).setGroupIndex(0);
                    clearSpellSlots(((WandItem) wandItem).spellItems);
                }
                return super.extractItem(slot, amount, simulate);
            }
        };
    }

    private void clearSpellSlots(SpellItem[] spellItems){
        for (int i = 1; i <= spellItems.length; i++)
        {
            itemStackHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }
    private void fillSpellSlots(SpellItem[] spellItems){
        for (int i = 0; i < spellItems.length;i++){
            if (spellItems[i] != null){
                itemStackHandler.setStackInSlot(i + 1, new ItemStack(spellItems[i]));
            }
        }
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
        if (itemStackHandler.getStackInSlot(0).getCount() == 0){
            return ItemStack.EMPTY;
        }
        WandItem wandItem = (WandItem) itemStackHandler.getStackInSlot(0).getItem();
        for (int i = 1;i <= wandItem.spellItems.length;i++){
            Item item = itemStackHandler.getStackInSlot(i).getItem();
            if (item instanceof SpellItem){
                wandItem.spellItems[i - 1] = (SpellItem) item;
            } else {
                wandItem.spellItems[i - 1] = null;
            }
        }
        return new ItemStack(wandItem);
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