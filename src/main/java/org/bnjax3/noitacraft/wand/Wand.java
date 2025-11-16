package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.item.ModItems;
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
        System.out.println(Arrays.toString(spellGroups));
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
                        System.out.println("cant wrap");
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
                        System.out.println("spells to draw : " + spellsToDraw);
                        spellsToDraw--;


                        if (spell instanceof MulticastSpell) {
                            spellsToDraw += ((MulticastSpell) spell).Draws;

                        } else if (spell instanceof PayloadSpell) {
                            SpellGroup payload = getTriggerPayload(spells, index + 1, ((PayloadSpell) spell).count, 1);
                            System.out.println("---------- We're outside the trigger --------------");
                            ((PayloadSpell) spell).payload = payload;
                            if (payload != null) {
                                System.out.println(payload.Spells);
                                // esto se saltearia los hechizos que hayan sido anadidos a la payload del trigger o timer
                                // (pero crashea a la re bosta)
                                index += payload.AmountOfSpells(0);
                            } else {
                                System.out.println("the payload is null");
                            }
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
            if (!spellGroupHash.isEmpty()) {
                // dudo mucho de esta funcion per oespero que ande
                spellGroups.add(new SpellGroup(new ArrayList<>(spellGroupHash.values()), this));
            }
            if (spellGroups.size() > 100){
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
    public SpellGroup getTriggerPayload(Spell[] spells, int indexToStart, int count, int recusionSteps){
        System.out.println("------ trying to get trigger payload ------");
        if (recusionSteps > 100){
            return null;
        }
        int index = indexToStart;
        // this is the index of the first payload spell in the recusion chain
        // so that if this index is found again it stops the function
        int indexOfTrigger = indexToStart - recusionSteps;
        int spellsToDraw = count;
        HashMap<Integer, Spell> payloadGroupHash = new HashMap<>(spells.length);
        while (spellsToDraw > 0){
            System.out.println(" ---* We're inside a trigger, index " + index);
            System.out.println(" ---* We're inside a trigger, og index " + indexOfTrigger);
            if (index >= spells.length){
                // try wrap
                index = 0;
                System.out.println("trying to wrap inside trigger");
                // if trying to wrap with a spell group that hasnt been added a spell
                // in the last iteration, cancel the wrap
                if (index == indexOfTrigger){
                    System.out.println("cant wrap inside trigger");
                    return null;
                }
            }
            if (index == indexOfTrigger){
                break;
            }
            Spell spell = spells[index];
            if (spell != null) {
                // its probably this thats breaking everything
                // fixed
                if (spell.countsTowardCast) {
                    System.out.println("spells to draw : " + spellsToDraw);
                    spellsToDraw--;


                    if (spell instanceof MulticastSpell) {
                        spellsToDraw += ((MulticastSpell) spell).Draws;

                    } else if (spell instanceof PayloadSpell) {
                        System.out.println("recursion steps : " + recusionSteps);
                        SpellGroup payload = getTriggerPayload(spells, index + 1, ((PayloadSpell) spell).count, recusionSteps + 1);
                        ((PayloadSpell) spell).payload = payload;
                        if (payload != null) {
                            //index += payload.AmountOfSpells();
                        }
                    }
                }
                payloadGroupHash.put(index, spell);

                System.out.println(" ---* index :" + index);
                System.out.println(" ---* spells to grab :" + spellsToDraw);
                index++;
            }
        }
        return new SpellGroup(new ArrayList<>(payloadGroupHash.values()), this);
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
