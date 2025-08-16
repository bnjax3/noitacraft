package org.bnjax3.noitacraft.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;


public class ModItemGroup {
    public static final ItemGroup NOITACRAFT_GROUP = new ItemGroup("Noitacraft"){
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TEST_ITEM.get());
        }
    };
}