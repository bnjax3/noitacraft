package org.bnjax3.noitacraft.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;


public class ModItemGroup {
    public static final ItemGroup NOITACRAFT_GROUP = new ItemGroup("Noitacraft"){
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TEST_ITEM.get());
        }
    };
    public static final ItemGroup WANDS_GROUP = new ItemGroup("Wands"){
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.STARTER_WAND.get());
        }
    };
    public static final ItemGroup SPELLS_GROUP = new ItemGroup("Spells"){
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SPARK_BOLT_ITEM.get());
        }
    };
}