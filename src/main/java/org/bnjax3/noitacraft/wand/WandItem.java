package org.bnjax3.noitacraft.wand;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.other.Utils;
import org.bnjax3.noitacraft.spell.SpellItem;
import org.bnjax3.noitacraft.spell.main_classes.Spell;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;

public class WandItem extends Item {

    public final Wand Wand1;
    public SpellItem[] spellItems;
    private int groupIndex = 0;
    public WandItem(Wand wand) {
        super(new Item.Properties().stacksTo(1).tab(ModItemGroup.WANDS_GROUP));
        Wand1 = wand;
        this.spellItems = new SpellItem[Wand1.Capacity];
    }
    public WandItem(Properties itemProperties, Wand wand, SpellItem[] spellItems) {
        super(itemProperties);
        Wand1 = wand;
        this.spellItems = spellItems;
    }
    /*
    public final boolean Shuffle;
    public final int SpellsCast;
    public final int ManaMax;
    public final int Capacity;
    public final int ManaChargeSpeed;
    public final int CastDelay; // in ticks
    public final float Spread; // degrees
    public final float SpeedMult;
    public final int RechargeTime; // in ticks
     */
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            // es un chingo de texto
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.shuffle", this.Wand1.Shuffle));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellsCast", this.Wand1.SpellsCast));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.castDelay", this.Wand1.CastDelay));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.rechargeTime", this.Wand1.RechargeTime));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.manaMax", this.Wand1.ManaMax));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.manaChargeSpeed", this.Wand1.ManaChargeSpeed));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.capacity", this.Wand1.Capacity));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spread", this.Wand1.Spread));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.speedMult", this.Wand1.SpeedMult));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellsInWand", Arrays.toString(this.spellItems)));
        } else {
            textComponents.add(new TranslationTextComponent("tooltip.noitacraft.shiftForDetail"));
        }
        super.appendHoverText(itemStack, world, textComponents, tooltipFlag);
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide){
            Spell[] spells = new Spell[27];
            for (int i = 0;i < spellItems.length;i++){
                SpellItem spellItem = spellItems[i];
                if (spellItem != null){
                    spells[i] = spellItem.spell;
                }
            }
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

    /*
    public boolean addSpellItem(ItemStack item){
        if (spells.length < Wand1.Capacity){
            if (item.getItem() instanceof SpellItem){
                spells[spells.length] = ((SpellItem) item.getItem()).spell;
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean removeSpellItem(ItemStack item){
        if (spells.length > 0){
            spells[spells.length - 1] = null;
            return true;
        }
        return false;
    }
    */
}
