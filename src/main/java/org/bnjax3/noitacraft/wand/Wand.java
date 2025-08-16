package org.bnjax3.noitacraft.wand;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public void Cast(ItemUseContext context, World world, Spell[] spells){
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        SpellGroup[] spellGroups = GroupSpells(spells);
        for (int i = 0; i < spells.length; i++){

        }

    }
    public SpellGroup[] GroupSpells(Spell[] spells){
        ArrayList<SpellGroup> spellGroups = new ArrayList<>();
        int spellsToGrab = SpellsCast;
        int count = 0;
        int index = 0;
        while (count <= spellsToGrab){
            ArrayList<Spell> Casted = new java.util.ArrayList<>();
            ArrayList<Spell> Modifiers = new java.util.ArrayList<>();
            ArrayList<Spell> Multicasts = new java.util.ArrayList<>();
            Spell spell = spells[index];
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
        }
        return (SpellGroup[]) spellGroups.toArray();
    }
}
