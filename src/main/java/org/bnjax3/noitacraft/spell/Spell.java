package org.bnjax3.noitacraft.spell;

public class Spell {
    public final int Uses;
    public final int ManaDrain;
    public final int CastDelay;
    public final int RechargeTime;
    public final float Spread;
    public final float Recoil;
    public final String Type;
    public final boolean countsTowardCast;
    public Spell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, String type, boolean countsTowardCast){
        Uses = uses;
        ManaDrain = manaDrain;
        CastDelay = castDelay;
        RechargeTime = rechargeTime;
        Spread = spread;
        Recoil = recoil;
        Type = type;
        this.countsTowardCast = countsTowardCast;
    }
}
