package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.Spell;

public class WandItem extends Item {
    public final Wand Wand1;
    public Spell[] spells;
    private int groupIndex = 0;
    public WandItem(Properties itemProperties, Wand wand) {
        super(itemProperties);
        Wand1 = wand;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();
        PlayerEntity player = context.getPlayer();
        if (!world.isClientSide){
            SpellGroup[] spellGroups = Wand1.GroupSpells(spells);
            if (!(groupIndex < spellGroups.length)){
                assert player != null;
                player.getCooldowns().addCooldown(this, Wand1.getFinalRechargeTime(spellGroups));
            }
            Wand1.Cast(context, world, spellGroups, groupIndex);
            groupIndex++;
        }

        return super.onItemUseFirst(stack, context);
    }
}
