package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.*;
import org.bnjax3.noitacraft.spell.main_classes.PayloadSpell;
import org.bnjax3.noitacraft.spell.main_classes.Spell;

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
    public void Cast(Entity entity, World world, Vector3d position, Vector3d viewVector){
        spellProperties = new SpellProperties(wand);
        for (Spell spell : Spells){
            spell.Modify(this);
        }
        for (Spell spell : Spells){
            spell.Cast(this, entity, world, position, viewVector);
        }


    }

    public int AmountOfSpells(){
            int count = 0;
            for(Spell spell1 : Spells){
                count++;
            }
            return count;
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
