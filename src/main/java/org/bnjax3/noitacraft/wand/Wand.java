package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.item.SpellItem;
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
    public final float CastDelay; // in seconds
    public final float Spread; // degrees
    public final float SpeedMult;
    // global through wand :
    public final float RechargeTime; // in seconds

    public Wand(boolean shuffle, int spellsCast, float castDelay, float rechargeTime, int manaMax, int manaChargeSpeed, int capacity, float spread, float speedMult) {
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
        // System.out.println("--- made it to the cast function!!");
        // System.out.println(Arrays.toString(spellGroups));
        spellGroups[groupIndex].Cast(player, world, player.getEyePosition(1), player.getViewVector(1));

    }


    public SpellGroup[] GroupSpellsInWand(Spell[] spells){
        // System.out.println("function called");
        // System.out.println("Spells in wand : \n"+Arrays.toString(spells));
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
                    // System.out.println("trying to wrap");
                    // if trying to wrap with a spell group that hasnt been added a spell
                    // in the last iteration, cancel the wrap
                    if (spellGroupHash.isEmpty()){
                        // System.out.println("cant wrap");
                        break;
                    }
                }
                Spell spell = spells[index];
                if (spell != null) {
                    // its probably this thats breaking everything
                    // fixed
                    if (groupAlreadyContains(spellGroupHash, index)) {
                        // System.out.println("reached end of wand");
                        reachedEndOfWand = true;
                        break;
                    }
                    if (spell.CountsToCast()) {
                        // System.out.println("spells to draw : " + spellsToDraw);
                        spellsToDraw--;


                        if (spell instanceof MulticastSpell) {
                            spellsToDraw += ((MulticastSpell) spell).getDraws();

                        } else if (spell instanceof PayloadSpell) {
                            SpellGroup payload = getTriggerPayload(spells, index + 1, ((PayloadSpell) spell).count, index);
                            // System.out.println("---------- We're outside the trigger of index " + index + " --------------");
                            if (spell instanceof TriggerSpell){
                                spell = new TriggerSpell((TriggerSpell) spell, payload);
                            } else if (spell instanceof TimerSpell){
                                spell = new TimerSpell((TimerSpell) spell, payload);
                            }
                            if (payload != null) {
                                // System.out.println(payload.Spells);
                                // esto se saltearia los hechizos que hayan sido anadidos a la payload del trigger o timer
                                // (pero crashea a la re bosta)
                                index += payload.AmountOfSpells(0);
                            } else {
                                // System.out.println("the payload is null");
                            }
                        }
                    }
                    spellGroupHash.put(index,spell);
                }
                // System.out.println(" ---* index :" + index);
                // System.out.println(" ---* spells to grab :" + spellsToDraw);
                // System.out.println(" ---* reached end of wand: " + reachedEndOfWand);
                index++;
            }
            if (!spellGroupHash.isEmpty()) {
                // dudo mucho de esta funcion per oespero que ande
                spellGroups.add(new SpellGroup(new ArrayList<>(spellGroupHash.values()), this));
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
    // reciem e doy cuenta que esta desactualizado respecto a la otra funcion AAAAAAAAAAAAAAAAA
    // o no, puede ser que no, no entiendo mi codigo
    public SpellGroup getTriggerPayload(Spell[] spells, int indexToStart, int count, int parentTriggerIndex){
        // System.out.println("------ trying to get trigger " + indexToStart + " payload ------");
        int index = indexToStart;
        // this is the index of the first payload spell in the recusion chain
        // so that if this index is found again it stops the function
        int spellsToDraw = count;
        HashMap<Integer, Spell> payloadGroupHash = new HashMap<>(spells.length);
        while (spellsToDraw > 0){
            // System.out.println(" ---* We're inside a trigger, index " + index);
            // System.out.println(" ---* We're inside a trigger, og index " + parentTriggerIndex);
            if (index >= spells.length){
                // try wrap
                index = 0;
                // System.out.println("trying to wrap inside trigger");
            }
            // if trying to wrap to the parent trigger, return no payload
            if (index == parentTriggerIndex){
                // System.out.println("attempted to draw parent trigger, payload is null for spell " + index);
                return null;
            }
            Spell spell = spells[index];
            if (spell != null) {
                // its probably this thats breaking everything
                // fixed
                if (spell.CountsToCast()) {
                    // System.out.println("spells to draw : " + spellsToDraw);
                    spellsToDraw--;

                    if (spell instanceof MulticastSpell) {
                        spellsToDraw += ((MulticastSpell) spell).getDraws();

                    } else if (spell instanceof PayloadSpell) {
                        SpellGroup payload = getTriggerPayload(spells, index + 1, ((PayloadSpell) spell).count, parentTriggerIndex);
                        if (spell instanceof TriggerSpell){
                            spell = new TriggerSpell((TriggerSpell) spell, payload);
                        } else if (spell instanceof TimerSpell){
                            spell = new TimerSpell((TimerSpell) spell, payload);
                        }
                        // System.out.println("Spell " + spell);
                        if (payload == null) {
                            System.out.println("Payload is checked as null for spell index " + index);
                        }
                    }
                }
                payloadGroupHash.put(index, spell);
            }
            index++;
        }
        return new SpellGroup(new ArrayList<>(payloadGroupHash.values()), this);
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

    public double getFinalRechargeTime(SpellItem[] spellItems){
        double toReturn = RechargeTime;
        for (SpellItem spellItem : spellItems){
            if (spellItem != null){
                toReturn += spellItem.spell.getRechargeTime();
            }
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
