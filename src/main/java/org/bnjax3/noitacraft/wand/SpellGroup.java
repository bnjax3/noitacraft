package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.*;
import org.bnjax3.noitacraft.spell.main_classes.PayloadSpell;
import org.bnjax3.noitacraft.spell.main_classes.Spell;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;

public class SpellGroup {
    public ArrayList<Spell> Spells;
    public final Wand wand;
    public SpellProperties spellProperties;
    public SpellGroup(ArrayList<Spell> spells, Wand wand) {
        Spells = spells;
        this.wand = wand;
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

    public int AmountOfSpells(int recursionStep){
            recursionStep++;
            System.out.println("Recursion Step : " + recursionStep);
            int count = 0;
            if (recursionStep > 10){
                return 0;
            }
            for (Spell spell : this.Spells){
                if (spell != null){
                    count++;
                    System.out.println(spell);
                    System.out.println(spell.hasPayload());
                    if (spell.hasPayload()){
                        count += ((PayloadSpell) spell).payload.AmountOfSpells(recursionStep);
                    }
                    System.out.println("Counted : " + count);
                }
            }
            return count;
        }


    public int GetRechargeTimeModifier(){
        int toReturn = 0;
        for (Spell spell : Spells){
            toReturn += spell.RechargeTime;
            if (spell.hasPayload()){
                toReturn += ((PayloadSpell) spell).payload.GetRechargeTimeModifier();
            }
        }
        return toReturn;
    }


    @Override
    public String toString() {
        return "SpellGroup{ " +
                "Spells =" + Spells +
                " }";
    }
    public void append(Spell spell){
        Spells.add(spell);
    }
}
