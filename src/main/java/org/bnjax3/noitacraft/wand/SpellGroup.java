package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.*;

import java.util.ArrayList;

public class SpellGroup {
    public ArrayList<Spell> Casted;
    public ArrayList<Spell> Modifiers;
    public final int IndexGroupStart;
    public final Wand wand;
    public SpellProperties spellProperties;
    public SpellGroup(ArrayList<Spell> casted, ArrayList<Spell> modifiers, int indexGroupStart, Wand wand) {
        Casted = casted;
        Modifiers = modifiers;
        IndexGroupStart = indexGroupStart;
        this.wand = wand;
    }
    public SpellGroup(ArrayList<Spell> casted, ArrayList<Spell> modifiers, Wand wand){
        this(casted, modifiers, 0, wand);
    }
    public void Cast(PlayerEntity player, ItemStack stack, World world, ItemUseContext context){
        spellProperties = new SpellProperties();
        for (Spell spell : Casted){
            if (!(spell instanceof MulticastSpell)){
                spell.Cast(this, player, stack, world, context);
            }

        }
    }

    public int AmountOfSpells(){
            int count = 0;
            for(Spell spell1 : Casted){
                count++;
            }
            for(Spell spell1 : Modifiers){
                count++;
            }
            return count;
        }
        // [min, max]
        // dowsnt work with spell wrapping
        public int[] RangeOfSpellGroup(){
            return new int[]{IndexGroupStart, IndexGroupStart + AmountOfSpells() - 1};
        }

        public int GetRechargeTimeModifier(){
            int toReturn = 0;
            for (Spell spell : this.Casted){
                toReturn += spell.RechargeTime;
                if (spell instanceof PayloadSpell){
                    toReturn += ((PayloadSpell) spell).payload.GetRechargeTimeModifier();
                }
            }
            for (Spell spell : this.Modifiers){
                toReturn += spell.RechargeTime;
            }
            return toReturn;
        }

    public void SumModifiers(){
        for (Spell spell : Modifiers){
            spellProperties.Change(spell);
        }
        for (Spell spell : Casted){
            spellProperties.Change(spell);
        }
    }







}
