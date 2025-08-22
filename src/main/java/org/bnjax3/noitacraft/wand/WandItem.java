package org.bnjax3.noitacraft.wand;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.Spell;

import javax.annotation.ParametersAreNonnullByDefault;

public class WandItem extends Item {

    public final Wand Wand1;
    public Spell[] spells;
    private int groupIndex = 0;
    public WandItem(Properties itemProperties, Wand wand) {
        super(new Properties().stacksTo(1));
        Wand1 = wand;
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide){
            SpellGroup[] spellGroups = Wand1.GroupSpells(spells);
            if (!(groupIndex < spellGroups.length)){
                player.getCooldowns().addCooldown(this, Wand1.getFinalRechargeTime(spellGroups));
                groupIndex = 0;
            }
            Wand1.Cast(world, player, spellGroups, groupIndex);
            groupIndex++;
        }
        return super.use(world, player, hand);
    }


}
