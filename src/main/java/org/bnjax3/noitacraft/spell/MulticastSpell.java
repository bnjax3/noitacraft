package org.bnjax3.noitacraft.spell;

public class MulticastSpell extends Spell{
    public final int Draws;
    public MulticastSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, int draws) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, 0,  true);
        Draws = draws;
    }
}
