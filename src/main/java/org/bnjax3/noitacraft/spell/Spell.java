package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class Spell {
    public final int Uses;
    public final int ManaDrain;
    public final int CastDelay;
    public final int RechargeTime;
    public final float Spread;
    public final float Recoil;
    public final boolean countsTowardCast;
    public Spell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, boolean countsTowardCast){
        Uses = uses;
        ManaDrain = manaDrain;
        CastDelay = castDelay;
        RechargeTime = rechargeTime;
        Spread = spread;
        Recoil = recoil;
        this.countsTowardCast = countsTowardCast;
    }
    public void Cast(SpellGroup spellGroup, PlayerEntity player, ItemStack stack, World world, ItemUseContext context){
        ExecuteBeforeCast(player,stack,world,context);
        ModifyOther(spellGroup);
        ExecuteOnCast(player,stack,world,context);
        ExecuteAfterCast(player,stack,world,context);
    }

    public void ExecuteOnCast(PlayerEntity player, ItemStack stack, World world, ItemUseContext context){

    }
    public void ExecuteBeforeCast(PlayerEntity player, ItemStack stack, World world, ItemUseContext context){

    }
    public void ExecuteAfterCast(PlayerEntity player, ItemStack stack, World world, ItemUseContext context){

    }
    public void ModifyOther(SpellGroup spellGroup){

    }
}
