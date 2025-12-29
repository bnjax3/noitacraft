package org.bnjax3.noitacraft.spell.main_classes;

public class MulticastSpell extends ModifierSpell{
    private final int draws;
    public MulticastSpell(int manaDrain, int draws) {
        super(manaDrain);
        this.draws = draws;
    }

    @Override
    public boolean CountsToCast() {
        return true;
    }

    public int getDraws() {
        return draws;
    }
}
