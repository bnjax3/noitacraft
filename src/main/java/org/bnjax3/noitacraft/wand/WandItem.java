package org.bnjax3.noitacraft.wand;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.registry.ModItemGroup;
import org.bnjax3.noitacraft.other.Utils;
import org.bnjax3.noitacraft.item.SpellItem;
import org.bnjax3.noitacraft.spell.main_classes.Spell;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WandItem extends Item {

    public final Wand Wand1;
    // public SpellItem[] spellItems;
    private int groupIndex = 0;

    public WandItem(Wand wand) {
        super(new Item.Properties().stacksTo(1).tab(ModItemGroup.WANDS_GROUP));
        Wand1 = wand;
    }

    public SpellItem[] getSpellItems(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();

        if (!tag.contains("SpellItems")) {
            // creo uno nuevo si no existe
            SpellItem[] spellItems = new SpellItem[Wand1.Capacity];
            tag.put("SpellItems", Utils.toNBT(spellItems));
            return spellItems;
        }

        // leer desde NBT existente
        return Utils.fromNBT(tag.getCompound("SpellItems"));
    }

    /** Guarda datos en el stack */
    public void setSpellItems(ItemStack stack, SpellItem[] spellItems) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.put("SpellItems", Utils.toNBT(spellItems));
    }

    public static int getMana(ItemStack stack) {
        return stack.getOrCreateTag().getInt("mana");
    }

    public static void setMana(ItemStack stack, int value) {
        stack.getOrCreateTag().putInt("mana", value);
    }

    @Override
    @ParametersAreNonnullByDefault
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
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellsInWand", Arrays.toString(getSpellItems(itemStack))));
        } else {
            textComponents.add(new TranslationTextComponent("tooltip.noitacraft.shiftForDetail"));
        }
        super.appendHoverText(itemStack, world, textComponents, tooltipFlag);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean b) {
        if (world.isClientSide) {return;}
        setMana(itemStack, getMana(itemStack) + Math.round(Wand1.ManaChargeSpeed / 20f));
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide){
            SpellItem[] spellItems = getSpellItems(player.getItemInHand(hand));
            // System.out.println(Arrays.toString(spellItems));
            ArrayList<Spell> spells = new ArrayList<>();
            // initializes spells with the spellItem array
            // Removes all spells that are too expensive or null
            int budget = getMana(player.getItemInHand(hand));
            for (SpellItem spellItem : spellItems) {
                if (spellItem != null){
                    if (spellItem.spell.getManaDrain() <= budget) {
                        budget -= spellItem.spell.getManaDrain();
                        spells.add(spellItem.spell);
                    } else {
                        spells.add(null);
                    }
                } else {
                    spells.add(null);
                }
            }
            // Shuffle
            if (this.Wand1.Shuffle){
                Random random = new Random();
                for (int i = 0; i < spells.size(); i++){
                    // swap the place of i with some random index
                    int r = random.nextInt(spells.size());
                    Spell temp = spells.get(r);
                    spells.set(r, spells.get(i));
                    spells.set(i, temp);
                }
            }
            SpellGroup[] spellGroups = Wand1.GroupSpellsInWand(spells.toArray(new Spell[0]));
            // System.out.println(Arrays.toString(spellGroups));
            if (spellGroups.length == 0){
                // System.out.println("no spellgroups, skipping");
                return super.use(world, player, hand);
            }
            // System.out.println("Outside the groupSpells function");
            if (groupIndex >= spellGroups.length) {
                // System.out.println("Recharging spells....");
                double rechargeTime = Wand1.getFinalRechargeTime(spellItems);
                if (rechargeTime > 0){
                    player.getCooldowns().addCooldown(this, Utils.secsToTicks(rechargeTime));
                }
                // System.out.println(Utils.secsToTicks(Wand1.getFinalRechargeTime(spellItems)));
                groupIndex = 0;
            }

            Wand1.Cast(world, player, spellGroups, groupIndex);
            setMana(player.getItemInHand(hand), budget);
            // System.out.println("Applying Cast Delay...");
            double CD = spellGroups[groupIndex].getSpellProperties().getCastDelay();
            if (CD > 0) {
                player.getCooldowns().addCooldown(this, Utils.secsToTicks(CD));
            }
            // System.out.println(Utils.secsToTicks(spellGroups[groupIndex].getSpellProperties().getCastDelay()));
            groupIndex++;

        }
        return super.use(world, player, hand);
    }
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getMana(stack) / Wand1.ManaMax != 1;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        int value = getMana(stack);
        int max = 100;

        // IMPORTANT: this is inverted (0 = full, 1 = empty)
        return 1.0 - ((double) value / max);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0x2222FF; // color
    }
    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }
}
