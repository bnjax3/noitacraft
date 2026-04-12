package org.bnjax3.noitacraft.item;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.other.Utils;
import org.bnjax3.noitacraft.spell.main_classes.ModifierSpell;
import org.bnjax3.noitacraft.spell.main_classes.MulticastSpell;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.main_classes.Spell;

import javax.annotation.Nullable;
import java.util.List;

public class SpellItem extends Item {
    public Spell spell;
    public int usesLeft;
    public String spellName;
    public SpellItem(Properties p_i48487_1_, Spell spell, String spellName) {
        super(p_i48487_1_);
        this.spell = spell;
        this.spellName = spellName;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            // es un chingo de texto
            textComponents.add(Utils.FormatSpellDescription(this));
            textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.manaDrain", this.spell.getManaDrain()));
            if (spell.getUses() != -1){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.uses", usesLeft + "/" + this.spell.getUses()));
            }
            if (spell.getCastDelay() > 0){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellCastDelay", this.spell.getCastDelay()));
            }
            if (spell.getRechargeTime() > 0){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellRechargeTime", this.spell.getRechargeTime()));
            }
            if (spell.getSpread() != 0){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.spellSpread", this.spell.getSpread()));
            }
            if (spell.getRecoil() < 0) {
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.recoil", this.spell.getRecoil()));
            }
            if (spell instanceof MulticastSpell){
                textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.draws", ((MulticastSpell) spell).getDraws()));
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
                if (((ProjectileSpell) spell).getCritChance() != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.critChanceBonus", "+" +((ProjectileSpell) spell).getCritChance() + "%"));
                }
                if (((ProjectileSpell) spell).getDamage() != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.damage", ((ProjectileSpell) spell).getDamage()));
                }
                if (((ProjectileSpell) spell).getProjectileBounces() != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.bounces", ((ProjectileSpell) spell).getProjectileBounces()));
                }
            }
            if (spell instanceof ModifierSpell){
                if (spell.getCritChanceBonus() != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.critChanceBonus", "+" + spell.getCritChanceBonus() + "%"));
                }
                if (spell.getDamageBonus() != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.damage", spell.getDamageBonus()));
                }
                if (spell.getBounces() != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.bounces", spell.getBounces()));
                }
                if (spell.getSpeedMult() != 1){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.speedMult", spell.getSpeedMult()));
                }
                if (spell.getLifetime() != 0){
                    textComponents.add(Utils.FormatTooltipData("tooltip.noitacraft.lifetimeAdd", spell.getLifetime()));
                }
            }
        } else {
            textComponents.add(new TranslationTextComponent("tooltip.noitacraft.shiftForDetail"));
        }
        super.appendHoverText(itemStack, world, textComponents, tooltipFlag);
    }
}
