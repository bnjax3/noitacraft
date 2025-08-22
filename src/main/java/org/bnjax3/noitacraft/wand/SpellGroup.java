package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.*;

import java.util.ArrayList;

public class SpellGroup {
    public ArrayList<Spell> Spells;
    public final int IndexGroupStart;
    public final Wand wand;
    public SpellProperties spellProperties;
    public SpellGroup(ArrayList<Spell> spells, int indexGroupStart, Wand wand) {
        Spells = spells;
        IndexGroupStart = indexGroupStart;
        this.wand = wand;
    }
    public SpellGroup(ArrayList<Spell> spells, Wand wand){
        this(spells, 0, wand);
    }
    public void Cast(PlayerEntity player, World world){
        spellProperties = new SpellProperties(wand);
        for (Spell spell : Spells){
            spell.Modify(this);
        }
        for (Spell spell : Spells){
            spell.Cast(this, player, world);
        }


    }

    public int AmountOfSpells(){
            int count = 0;
            for(Spell spell1 : Spells){
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
            for (Spell spell : Spells){
                toReturn += spell.RechargeTime;
                if (spell instanceof PayloadSpell){
                    toReturn += ((PayloadSpell) spell).payload.GetRechargeTimeModifier();
                }
            }
            return toReturn;
        }









}
