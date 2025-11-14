package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.main_classes.*;

import java.util.*;

public class Wand {
    // unchangeable
    public final boolean Shuffle;
    public final int SpellsCast;
    public final int ManaMax;
    public final int Capacity;
    public final int ManaChargeSpeed;
    // spells can change in  block:
    public final int CastDelay; // in ticks
    public final float Spread; // degrees
    public final float SpeedMult;
    // global through wand :
    public final int RechargeTime; // in ticks

    public Wand(boolean shuffle, int spellsCast, int castDelay, int rechargeTime, int manaMax, int manaChargeSpeed, int capacity, float spread, float speedMult) {
        Shuffle = shuffle;
        SpellsCast = spellsCast;
        CastDelay = castDelay;
        RechargeTime = rechargeTime;
        ManaMax = manaMax;
        ManaChargeSpeed = manaChargeSpeed;
        Capacity = capacity;
        Spread = spread;
        SpeedMult = speedMult;
    }

    public void Cast(World world, PlayerEntity player, SpellGroup[] spellGroups, int groupIndex){
        System.out.println("--- made it to the cast function!!");
        spellGroups[groupIndex].Cast(player, world, player.getEyePosition(1), player.getViewVector(1));

    }


    public SpellGroup[] GroupSpellsInWand(Spell[] spells){
        System.out.println("function called");
        ArrayList<SpellGroup> spellGroups = new ArrayList<>();
        boolean reachedEndOfWand = false;
        int index = 0; // index of the current spell
        // cycles through groups
        while (!reachedEndOfWand){
            int spellsToDraw = SpellsCast;
            HashMap<Integer, Spell> spellGroupHash = new HashMap<>(spells.length);
            // cycles through spells
            while (spellsToDraw > 0){
                if (index >= spells.length){
                    // try wrap
                    index = 0;
                    reachedEndOfWand = true;
                    System.out.println("trying to wrap");
                    // if trying to wrap with a spell group that hasnt been added a spell
                    // in the last iteration, cancel the wrap
                    if (spellGroupHash.isEmpty()){
                        System.out.println("failed wrap");
                        break;
                    }
                }
                Spell spell = spells[index];
                if (spell != null) {
                    // its probably this thats breaking everything
                    // fixed
                    if (groupAlreadyContains(spellGroupHash, index)) {
                        System.out.println("reached end of wand");
                        reachedEndOfWand = true;
                        break;
                    }
                    if (spell.countsTowardCast) {
                        spellsToDraw--;
                        System.out.println("spells to draw decreased : " + spellsToDraw);

                        if (spell instanceof MulticastSpell) {
                            spellsToDraw += ((MulticastSpell) spell).Draws;

                        } else if (spell instanceof PayloadSpell) {
                            SpellGroup payload = getTriggerPayload(spells, index + 1, ((PayloadSpell) spell).count);
                            ((PayloadSpell) spell).payload = payload;
                            // creo que no tiene que tener en cuenta el wrap esto pero si termina fallando puede ser que sea eso
                            // esto se saltea los hechizos que hayan sido anadidos a la payload del trigger o timer
                            index += payload.AmountOfSpells() - 1;
                        }
                    }
                    spellGroupHash.put(index,spell);
                    System.out.println(" ---* index :" + index);
                    System.out.println(" ---* spells to grab :" + spellsToDraw);
                    System.out.println(" ---* reached end of wand: " + reachedEndOfWand);
                    index++;
                }
                if (spellGroupHash.size() > 100){
                    System.out.println("1st emercency stop");
                    return null;

                }
            }
            // dudo mucho de esta funcion per oespero que ande
            spellGroups.add(new SpellGroup(new ArrayList<>(spellGroupHash.values()), this));
            if (spellGroups.size() > 1000){
                System.out.println("2nd emercency stop");
                return null;

            }
        }

        return spellGroups.toArray(new SpellGroup[0]);
    }
    private boolean isArrayNull(Spell[] spells){
        for (Spell s: spells) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }
    // will see if a certain spell is already on the group by comparing the index (slot of the wand) its in
    // (two spells should not be able to have the same index)
    private boolean groupAlreadyContains(HashMap<Integer, Spell> groupHash, int indexOfSpell) {
        for (int i : groupHash.keySet()){
            if (i == indexOfSpell){
                return true;
            }
        }
        return false;
    }

    // reciem e doy cuenta que esta desactualizado respecto a la otra funcion AAAAAAAAAAAAAAAAA
    // o no, puede ser que no, no entiendo mi codigo
    public SpellGroup getTriggerPayload(Spell[] spells, int indexToStart, int count){
        System.out.println("tryng to get trigger payload");
        int index = indexToStart;
        int countedSpells = 0;
        int toDraw = count;
        ArrayList<Spell> toCast = new ArrayList<>();
        while (countedSpells < toDraw){
            // triggers are missing
            if (index >= spells.length){
                // try wrap
                index = 0;
                System.out.println("attempting wrap");
            }
            Spell spell = spells[index];
            // hay que poner fe en que esta funcion diferencia entre objetos distintos de misma clase y propiedades
            // por las dudas de que me haya mandado alguna cagada con le codigo
            if (toCast.contains(spell)){
                System.out.println("failed wrap");
                break;
            }

            if (spell.countsTowardCast){
                countedSpells++;
                if (spell instanceof MulticastSpell){
                    toDraw += ((MulticastSpell) spell).Draws;
                } else if (spell instanceof TriggerSpell) {
                    SpellGroup payload = getTriggerPayload(spells,index + 1, ((TriggerSpell) spell).count);
                    ((TriggerSpell) spell).payload = payload;
                    toCast.add(spell);
                    // creo que no tiene que tener en cuenta el wrap esto pero si termina fallando puede ser que sea eso
                    // esto se saltea los hechizos que hayan sido anadidos a la payload del trigger o timer
                    index += payload.AmountOfSpells() - 1;
                } else if (spell instanceof TimerSpell){
                    SpellGroup payload = getTriggerPayload(spells,index + 1, ((TimerSpell) spell).count);
                    ((TimerSpell) spell).payload = payload;
                    toCast.add(spell);
                    index += payload.AmountOfSpells() - 1;
                }
                else {
                    toCast.add(spell);
                }
            } else {
                toCast.add(spell);
            }
            index++;
        }
        return new SpellGroup(toCast,this);
    }

    public int getFinalRechargeTime(SpellGroup[] spellGroups){
        int toReturn = RechargeTime;
        for(SpellGroup spellGroup : spellGroups)
        {
            toReturn += spellGroup.GetRechargeTimeModifier();
        }
        return toReturn;
    }



    @Override
    public String toString() {
        return "Wand{" +
                "Shuffle=" + Shuffle +
                ", SpellsCast=" + SpellsCast +
                ", CastDelay=" + CastDelay +
                ", RechargeTime=" + RechargeTime +
                ", ManaMax=" + ManaMax +
                ", ManaChargeSpeed=" + ManaChargeSpeed +
                ", Capacity=" + Capacity +
                ", Spread=" + Spread +
                ", SpeedMult=" + SpeedMult +
                '}';
    }
}
