package org.bnjax3.noitacraft.wand;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.progress.StartupMessageManager;
import org.apache.logging.log4j.core.config.builder.api.Component;
import org.apache.logging.log4j.message.Message;
import org.bnjax3.noitacraft.other.Utils;
import org.bnjax3.noitacraft.spell.Spell;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.util.List;

public class WandItem extends Item {

    public final Wand Wand1;
    public Spell[] spells;
    private int groupIndex = 0;
    public WandItem(Properties itemProperties, Wand wand) {
        super(itemProperties);
        Wand1 = wand;
    }
    public WandItem(Properties itemProperties, Wand wand, Spell[] spells) {
        super(itemProperties);
        Wand1 = wand;
        this.spells = spells;
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
        } else {
            textComponents.add(new TranslationTextComponent("tooltip.noitacraft.shiftForDetail"));
        }
        super.appendHoverText(itemStack, world, textComponents, tooltipFlag);
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
