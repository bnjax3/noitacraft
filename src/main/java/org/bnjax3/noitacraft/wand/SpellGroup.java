package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.MulticastSpell;
import org.bnjax3.noitacraft.spell.Spell;

import java.util.ArrayList;

public class SpellGroup {
    public ArrayList<Spell> Casted;
    public ArrayList<Spell> Modifiers;
    public final int IndexGroupStart;
    public SpellGroup(ArrayList<Spell> casted, ArrayList<Spell> modifiers, int indexGroupStart) {
        Casted = casted;
        Modifiers = modifiers;
        IndexGroupStart = indexGroupStart;

    }
    public SpellGroup(ArrayList<Spell> casted, ArrayList<Spell> modifiers){
        this(casted, modifiers, 0);
    }
    public void Cast(PlayerEntity player, ItemStack stack, World world){

        for (Spell spell : Casted){
            if (!(spell instanceof MulticastSpell)){
                spell.Cast();
            }

        }
    }

    public boolean IsSpellInGroup(Spell spell, int indexOfSpell){
        int[] range = RangeOfSpellGroup();
        if (indexOfSpell >= range[0] && indexOfSpell <= range[1]){
            return true;
        }
        return false;
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






}
