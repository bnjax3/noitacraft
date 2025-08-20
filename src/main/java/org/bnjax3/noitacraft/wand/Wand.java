package org.bnjax3.noitacraft.wand;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.*;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
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

    public void Cast(ItemUseContext context, World world, SpellGroup[] spellGroups, int groupIndex){
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        spellGroups[groupIndex].Cast(player, stack, world, context);

    }
    public SpellGroup[] GroupSpells(Spell[] spells){
        ArrayList<SpellGroup> toReturn = new ArrayList<>();
        boolean reachedEndOfWand = false;
        while (!reachedEndOfWand){
            int index = 0;
            int countedSpells = 0;
            int toDraw = SpellsCast;
            ArrayList<Spell> casted = new ArrayList<>();
            ArrayList<Spell> modifiers = new ArrayList<>();
            while (countedSpells < toDraw){
                // triggers are missing
                if (index >= spells.length){
                    // try wrap
                    index = 0;
                    reachedEndOfWand = true;
                }
                Spell spell = spells[index];
                // hay que poner fe en que esta funcion diferencia entre objetos distintos de misma clase y propiedades
                if (casted.contains(spell)){
                    reachedEndOfWand = true;
                    break;
                }

                if (spell.countsTowardCast){
                    countedSpells++;
                    if (spell instanceof MulticastSpell){
                        toDraw += ((MulticastSpell) spell).Draws;
                    } else if (spell instanceof TriggerSpell) {
                        SpellGroup payload = getTriggerPayload(spells,index + 1, ((TriggerSpell) spell).count);
                        casted.add( new TriggerSpell( ((TriggerSpell) spell), payload ));
                        // creo que no tiene que tener en cuenta el wrap esto pero si termina fallando puede ser que sea eso
                        // esto se saltea los hechizos que hayan sido anadidos a la payload del trigger o timer
                        index += payload.AmountOfSpells() - 1;
                    } else if (spell instanceof TimerSpell){
                        SpellGroup payload = getTriggerPayload(spells,index + 1, ((TimerSpell) spell).count);
                        casted.add( new TimerSpell( ((TimerSpell) spell), payload));
                        index += payload.AmountOfSpells() - 1;
                    }
                    else {
                        casted.add(spell);
                    }
                } else {
                    modifiers.add(spell);
                }
                index++;
            }
            toReturn.add(new SpellGroup(casted, modifiers, index, this));
        }
        return toReturn.toArray(new SpellGroup[0]);

    }
    public SpellGroup getTriggerPayload(Spell[] spells, int indexToStart, int count){
        int index = indexToStart;
        int countedSpells = 0;
        int toDraw = count;
        ArrayList<Spell> casted = new ArrayList<>();
        ArrayList<Spell> modifiers = new ArrayList<>();
        while (countedSpells < toDraw){
            if (index == spells.length){
                // try wrap
                index = 0;
            }
            Spell spell = spells[index];
            if (casted.contains(spell)){
                break;
            }
            if (spell.countsTowardCast){
                countedSpells++;
                if (spell instanceof MulticastSpell){
                    toDraw += ((MulticastSpell) spell).Draws;
                } else if (spell instanceof TriggerSpell) {
                    SpellGroup payload = getTriggerPayload(spells,index + 1, ((TriggerSpell) spell).count);
                    casted.add( new TriggerSpell( ((TriggerSpell) spell), payload ));
                    // creo que no tiene que tener en cuenta el wrap esto pero si termina fallando puede ser que sea eso
                    // esto se saltea los hechizos que hayan sido anadidos a la payload del trigger o timer
                    index += payload.AmountOfSpells() - 1;
                } else if (spell instanceof TimerSpell){
                    SpellGroup payload = getTriggerPayload(spells,index + 1, ((TimerSpell) spell).count);
                    casted.add( new TimerSpell( ((TimerSpell) spell), payload));
                    index += payload.AmountOfSpells() - 1;
                }
                else {
                    casted.add(spell);
                }
            } else {
                modifiers.add(spell);
            }
            index++;
        }
        return new SpellGroup(casted, modifiers,this);
    }

    public int getFinalRechargeTime(SpellGroup[] spellGroups){
        int toReturn = RechargeTime;
        for(SpellGroup spellGroup : spellGroups)
        {
            toReturn += spellGroup.GetRechargeTimeModifier();
        }
        return toReturn;
    }

}
