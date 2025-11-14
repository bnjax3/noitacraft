package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.projectiles.MagicProjectile;
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
    public void Cast(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation){
        ExecuteBeforeCast(spellGroup, entity, world, position, rotation);
        ExecuteOnCast(spellGroup, entity, world, position, rotation);
        ExecuteAfterCast(spellGroup, entity, world, position, rotation);
    }
    public void Modify(SpellGroup spellGroup){
        // applies the properties of this spell to the spell group
        spellGroup.spellProperties.Change(this);
    }

    public void ExecuteOnCast(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation){

    }
    public void ExecuteBeforeCast(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation){

    }
    public void ExecuteAfterCast(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation){

    }
    public void ExecuteOnProjectileTick(MagicProjectile projectile){
        // function is executed by every projectile in the spell group every tick
    }

    public void ExecuteOnDeath(PlayerEntity owner, World level, MagicProjectile magicProjectile) {

    }

    @Override
    public String toString() {
        return "Spell: {" +
                "Uses=" + Uses +
                ", ManaDrain=" + ManaDrain +
                ", CastDelay=" + CastDelay +
                ", RechargeTime=" + RechargeTime +
                ", Spread=" + Spread +
                ", Recoil=" + Recoil +
                ", countsTowardCast=" + countsTowardCast +
                '}';
    }
}
