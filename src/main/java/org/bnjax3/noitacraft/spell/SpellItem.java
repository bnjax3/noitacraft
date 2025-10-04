package org.bnjax3.noitacraft.spell;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.other.Utils;

import javax.annotation.Nullable;
import java.util.List;

public class SpellItem extends Item {
    public Spell spell;
    public int usesLeft;
    public SpellItem(Properties p_i48487_1_, Spell spell) {
        super(p_i48487_1_);
        this.spell = spell;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            // es un chingo de texto
            textComponents.add(Utils.FormatSpellDescription(this));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.manaDrain", this.spell.ManaDrain));
            if (spell.Uses != -1){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.uses", usesLeft + "/" + this.spell.Uses));
            }
            if (spell.CastDelay > 0){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellCastDelay", this.spell.CastDelay));
            }
            if (spell.RechargeTime > 0){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellRechargeTime", this.spell.RechargeTime));
            }
            if (spell.Spread != 0){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellSpread", this.spell.Spread));
            }
            if (spell.Recoil < 0) {
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.recoil", this.spell.Recoil));
            }
            if (spell instanceof MulticastSpell){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.draws", ((MulticastSpell) spell).Draws));
            }
            if (spell instanceof ProjectileSpell){
                /*
                the speed should not be shown
                if (((ProjectileSpell) spell).speed != 0) {
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellSpeed", ((ProjectileSpell) spell).speed));
                }
                if (((ProjectileSpell) spell).lifetime != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.lifetime", ((ProjectileSpell) spell).lifetime));
                }
                 */
                if (((ProjectileSpell) spell).critChanceBonus != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.critChanceBonus", "+" +((ProjectileSpell) spell).critChanceBonus + "%"));
                }
                if (((ProjectileSpell) spell).damage != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.damage", ((ProjectileSpell) spell).damage));
                }
                if (((ProjectileSpell) spell).bounces != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.bounces", ((ProjectileSpell) spell).bounces));
                }
            }
            if (spell instanceof ModifierSpell){
                if (((ModifierSpell) spell).critChanceBonus != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.critChanceBonus", "+" + ((ModifierSpell) spell).critChanceBonus + "%"));
                }
                if (((ModifierSpell) spell).damage != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.damage", ((ModifierSpell) spell).damage));
                }
                if (((ModifierSpell) spell).bounces != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.bounces", ((ModifierSpell) spell).bounces));
                }
            }
        } else {
            textComponents.add(new TranslationTextComponent("tooltip.noitacraft.shiftForDetail"));
        }
        super.appendHoverText(itemStack, world, textComponents, tooltipFlag);
    }
}
