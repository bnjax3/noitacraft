package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.main_classes.*;

import java.util.ArrayList;

public class SpellGroup {
    public ArrayList<Spell> Spells;
    public final Wand wand;
    private SpellProperties spellProperties;
    public SpellGroup(ArrayList<Spell> spells, Wand wand) {
        Spells = spells;
        this.wand = wand;
        spellProperties = new SpellProperties(wand);
    }

    public int Cast(Entity entity, World world, Vector3d position, Vector3d viewVector, int spendableMana){
        for (Spell spell : Spells){
            spell.Modify(this);
        }
        for (Spell spell : Spells){
            spell.Cast(this, entity, world, position, viewVector);
        }
        return 0;
    }


    public void Modify(){
        for (Spell spell : Spells){
            spell.Modify(this);
        }
    }

    public int AmountOfSpells(int recursionStep){
            recursionStep++;
            int count = 0;
            if (recursionStep > 40){
                return 0;
            }
            for (Spell spell : this.Spells){
                if (spell != null){
                    count++;
                    if (spell.hasPayload()){
                        if (spell instanceof LoadedTimerSpell){
                            count += ((LoadedTimerSpell) spell).payload.AmountOfSpells(recursionStep);
                        } else if (spell instanceof LoadedTriggerSpell) {
                            count += ((LoadedTriggerSpell) spell).payload.AmountOfSpells(recursionStep);
                        }
                    }
                }
            }
            return count;
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

    public boolean isEmpty() {
        for (Spell spell : Spells){
            if (spell != null){
                return false;
            }
        }
        return true;
    }

    public SpellProperties getSpellProperties() {
        return spellProperties;
    }

    public void setSpellProperties(SpellProperties spellProperties) {
        this.spellProperties = spellProperties;
    }
}
