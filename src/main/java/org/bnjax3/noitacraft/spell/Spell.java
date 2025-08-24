package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
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
    public void Cast(SpellGroup spellGroup, PlayerEntity player, World world, Vector3d position, float xRot, float yRot){
        ExecuteBeforeCast(spellGroup, player, world, position, xRot, yRot);
        ExecuteOnCast(spellGroup, player, world, position, xRot, yRot);
        ExecuteAfterCast(spellGroup, player, world, position, xRot, yRot);
    }
    public void Modify(SpellGroup spellGroup){
        // applies the properties of this spell to the spell group
        spellGroup.spellProperties.Change(this);
    }

    public void ExecuteOnCast(SpellGroup spellGroup, PlayerEntity player, World world, Vector3d position, float xRot, float yRot){

    }
    public void ExecuteBeforeCast(SpellGroup spellGroup, PlayerEntity player, World world, Vector3d position, float xRot, float yRot){

    }
    public void ExecuteAfterCast(SpellGroup spellGroup, PlayerEntity player, World world, Vector3d position, float xRot, float yRot){

    }
    public void ExecuteOnProjectileTick(MagicProjectile projectile){
        // function is executed by every projectile in the spell group every tick
    }
}
