package org.bnjax3.noitacraft.spell.main_classes;

public class MulticastSpell extends ModifierSpell{
    private final int draws;
    public MulticastSpell(int draws, SpellProperties spellProperties) {
        super(spellProperties);
        this.draws = draws;
    }
    public MulticastSpell(int mana, int draws) {
        super(mana);
        this.draws = draws;
    }
    public MulticastSpell(int mana, int draws, float spread) {
        super(new SpellProperties().setSpread(spread).setManaDrain(mana));
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
