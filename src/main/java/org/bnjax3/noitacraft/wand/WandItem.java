package org.bnjax3.noitacraft.wand;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.item.ModItems;
import org.bnjax3.noitacraft.other.Utils;
import org.bnjax3.noitacraft.spell.SpellItem;
import org.bnjax3.noitacraft.spell.SpellsRegistry;
import org.bnjax3.noitacraft.spell.main_classes.Spell;
import org.bnjax3.noitacraft.spell.main_classes.TimerSpell;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
            ArrayList<Spell> spells = new ArrayList<>();
            // initializes spells with the spellItem array
            for (SpellItem spellItem : spellItems){
                if (spellItem != null){
                    spells.add(spellItem.spell);
                }
            }
            /*
            ArrayList<Spell> e = new ArrayList<>();
            e.add(SpellsRegistry.SPARK_BOLT);
            e.add(SpellsRegistry.SPARK_BOLT);
            SpellGroup sg1 = new SpellGroup(e,Wand1);
            e.clear();
            e.add(SpellsRegistry.SPARK_BOLT_TIMER);
            e.add(SpellsRegistry.SPARK_BOLT_TIMER);
            e.add(SpellsRegistry.SPARK_BOLT_TIMER);
            SpellGroup sg2 = new SpellGroup(e,Wand1);
            e.clear();
            TimerSpell spell = new TimerSpell(SpellsRegistry.SPARK_BOLT,1,23);
            spell.payload = sg2;
            e.add(spell);
            SpellGroup sg3 = new SpellGroup(e,Wand1);

            System.out.println("============== SEPARATE TEST =============");
            System.out.println("--- Calling AOF from sg1");
            System.out.println(sg1.AmountOfSpells(0));
            System.out.println("--- Calling AOF from sg2");
            System.out.println(sg2.AmountOfSpells(0));
            System.out.println("--- Calling AOF from sg3");
            System.out.println(sg3.AmountOfSpells(0));
            System.out.println("==========================================");
             */
            System.out.println("LA GRAN CUESTION .....................");
            System.out.println(spells);
            System.out.println(Arrays.toString(spells.toArray(new Spell[0])));
            SpellGroup[] spellGroups = Wand1.GroupSpellsInWand(spells.toArray(new Spell[0]));
            if (spellGroups != null) {
                if (groupIndex >= spellGroups.length) {
                    player.getCooldowns().addCooldown(this, Wand1.getFinalRechargeTime(spellGroups));
                    groupIndex = 0;
                }
                Wand1.Cast(world, player, spellGroups, groupIndex);
                player.getCooldowns().addCooldown(this, spellGroups[groupIndex].spellProperties.CastDelay);
                groupIndex++;
            }


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

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }
}
