package org.bnjax3.noitacraft.wand;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.Spell;
import org.bnjax3.noitacraft.wand.Wand;

public class WandItem extends Item {
    public final Wand Wand1;
    public static Spell[] spells;
    public WandItem(Properties itemProperties, Wand wand) {
        super(itemProperties);
        Wand1 = wand;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();
        if (!world.isClientSide){
            Wand1.Cast(context, world, spells);
        }

        return super.onItemUseFirst(stack, context);
    }
}
