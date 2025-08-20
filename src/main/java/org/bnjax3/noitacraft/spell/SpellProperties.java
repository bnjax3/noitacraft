package org.bnjax3.noitacraft.spell;

public class SpellProperties {
    public int CastDelay = 0;
    public float Spread = 0;
    public float Recoil = 0;
    public float speedMult = 1;
    public float critChanceBonus = 0;
    public float damageBonus = 0;
    public int lifetime = 0; // ticks
    public boolean friendlyFire = false;
    public int bounces = 0;
    // public float gravity = 0; // block/tick
    public SpellProperties(){

    }
    public void Change(Spell spell){
        CastDelay += spell.CastDelay;
        Spread += spell.Spread;
        Recoil += spell.Recoil;

        if (spell instanceof ModifierSpell){
            speedMult += ((ModifierSpell) spell).speedMult;
            critChanceBonus += ((ModifierSpell) spell).critChanceBonus;
            damageBonus += ((ModifierSpell) spell).damage;
            lifetime += ((ModifierSpell) spell).lifetime;
            if (((ModifierSpell) spell).friendlyFire){
                friendlyFire = true;
            }
            bounces += ((ModifierSpell) spell).bounces;
        }

    }
}
