package org.bnjax3.noitacraft.spell.main_classes;

import org.bnjax3.noitacraft.wand.Wand;

public class SpellProperties {
    private int manaDrain = 0;
    private float rechargeTime = 0;
    private float castDelay = 0;
    private float spread = 0;
    private float recoil = 0;
    private float speedMult = 1;
    private float critChanceBonus = 0;
    private float damageBonus = 0;
    private int lifetime = 0; // ticks
    private boolean friendlyFire = false;
    private int bounces = 0;
    private int pierces = 0;
    private boolean fullPiercing = false;


    // public float gravity = 0; // block/tick
    public SpellProperties(Wand wand){
        castDelay = wand.CastDelay;
        spread = wand.Spread;
    }
    public SpellProperties(){

    }
    public void Change(Spell spell){
        if (spell instanceof ModifierSpell){
            Change(spell.properties);
            return;
        }
        manaDrain += spell.properties.getManaDrain();
        castDelay += spell.properties.getCastDelay();
        spread += spell.properties.getSpread();
        recoil += spell.properties.getRecoil();
    }
    public void Change(SpellProperties properties){
        manaDrain += properties.getManaDrain();
        castDelay += properties.getCastDelay();
        spread += properties.getSpread();
        recoil += properties.getRecoil();
        speedMult += properties.getSpeedMult();
        critChanceBonus += properties.getCritChanceBonus();
        damageBonus += properties.getDamageBonus();
        lifetime += properties.getLifetime();
        if (properties.isFriendlyFire()){
            friendlyFire = true;
        }
        if (properties.isFullPiercing()){
            fullPiercing = true;
        }
        bounces += properties.getBounces();
        pierces += properties.getPierces();
    }

    // qlia puros nombres de mierda pongo a las funciones
    // PORQUE CARAJO HICE ESTO NO ME ACUERDO ??!?!?!??!
    /*
    public void ChangeByAll(Spell spell){

        critChanceBonus += ((ProjectileSpell) spell).critChanceBonus;
        damageBonus += ((ProjectileSpell) spell).damage;
        lifetime += ((ProjectileSpell) spell).lifetime;
        if (((ProjectileSpell) spell).friendlyFire){
            friendlyFire = true;
        }
        bounces += ((ProjectileSpell) spell).bounces;
    }
     */

    public float getCastDelay() {
        return castDelay;
    }

    public SpellProperties setCastDelay(float castDelay) {
        this.castDelay = castDelay;
        return this;
    }

    public float getSpread() {
        return spread;
    }

    public SpellProperties setSpread(float spread) {
        this.spread = spread;
        return this;
    }

    public float getRecoil() {
        return recoil;
    }

    public SpellProperties setRecoil(float recoil) {
        this.recoil = recoil;
        return this;
    }

    public float getSpeedMult() {
        return speedMult;
    }

    public SpellProperties setSpeedMult(float speedMult) {
        this.speedMult = speedMult;
        return this;
    }

    public float getCritChanceBonus() {
        return critChanceBonus;
    }

    public SpellProperties setCritChanceBonus(float critChanceBonus) {
        this.critChanceBonus = critChanceBonus;
        return this;
    }

    public float getDamageBonus() {
        return damageBonus;
    }

    public SpellProperties setDamageBonus(float damageBonus) {
        this.damageBonus = damageBonus;
        return this;
    }

    public int getLifetime() {
        return lifetime;
    }

    public SpellProperties setLifetime(int lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public boolean isFriendlyFire() {
        return friendlyFire;
    }

    public SpellProperties setFriendlyFire(boolean friendlyFire) {
        this.friendlyFire = friendlyFire;
        return this;
    }

    public int getBounces() {
        return bounces;
    }

    public SpellProperties setBounces(int bounces) {
        this.bounces = bounces;
        return this;
    }

    public int getPierces() {
        return pierces;
    }

    public SpellProperties setPierces(int pierces) {
        this.pierces = pierces;
        return this;
    }

    public boolean isFullPiercing() {
        return fullPiercing;
    }

    public SpellProperties setFullPiercing(boolean fullPiercing) {
        this.fullPiercing = fullPiercing;
        return this;
    }

    public float getRechargeTime() {
        return rechargeTime;
    }

    public SpellProperties setRechargeTime(float rechargeTime) {
        this.rechargeTime = rechargeTime;
        return this;
    }

    public int getManaDrain() {
        return manaDrain;
    }

    public SpellProperties setManaDrain(int manaDrain) {
        this.manaDrain = manaDrain;
        return this;
    }
}
