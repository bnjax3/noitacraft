package org.bnjax3.noitacraft.wand;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.ModifierSpell;
import org.bnjax3.noitacraft.spell.MulticastSpell;
import org.bnjax3.noitacraft.spell.Spell;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.*;

public class Wand {
    public final boolean Shuffle;
    public final int SpellsCast;
    public final int CastDelay; // in ticks
    public final int RechargeTime; // in ticks
    public final int ManaMax;
    public final int ManaChargeSpeed;
    public final int Capacity;
    public final float Spread; // degrees
    public final float SpeedMult;

    public Wand(boolean shuffle, int spellsCast, int castDelay, int rechargeTime, int manaMax, int manaChargeSpeed, int capacity, int spread, float speedMult) {
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

    public void Cast(ItemUseContext context, World world, Spell[] spells, int groupIndex){
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        SpellGroup[] spellGroups = GroupSpells(spells);

        spellGroups[groupIndex].Cast(player, stack, world);

    }
    public SpellGroup[] GroupSpells(Spell[] spells){
        ArrayList<SpellGroup> spellGroups = new ArrayList<>();
        int index = 0;
        if (Shuffle){
            //shufflear
            List<Spell> spellslist = Arrays.asList(spells);
            Collections.shuffle(spellslist);
            spells = spellslist.toArray(new Spell[0]);
        }
        while (spells.length > index){
            int spellsToGrab = SpellsCast;
            int count = 0;
            ArrayList<Spell> Casted = new ArrayList<>();
            ArrayList<Spell> Modifiers = new ArrayList<>();
            ArrayList<Spell> Multicasts = new ArrayList<>();
            while (count < spellsToGrab){
                if (spells.length > index){
                    Spell spell = spells[index];
                    // faltan triggers y timers
                    if (spell instanceof MulticastSpell){
                        spellsToGrab += ((MulticastSpell) spell).Draws;
                        Multicasts.add(spell);
                    }
                    if (spell.countsTowardCast){
                        count++;
                        Casted.add(spell);
                    } else {
                        Modifiers.add(spell);
                    }
                    index++;
                } else {
                    // try wrap
                    for (int i = 0; true; i++){
                        if (spells[i].countsTowardCast && !(spells[i] instanceof MulticastSpell)){
                            Casted.add(spells[i]);
                            break;
                        } else if (spells[i] instanceof ModifierSpell){
                            Modifiers.add(spells[i]);
                        }
                    }
                }
            }
            spellGroups.add(new SpellGroup(Casted, Modifiers, Multicasts));
        }
        return (SpellGroup[]) spellGroups.toArray();
    }

}
