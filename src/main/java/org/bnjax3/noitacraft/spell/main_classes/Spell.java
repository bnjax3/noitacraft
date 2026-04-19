package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class Spell {
    public SpellProperties properties;
    private int uses = -1;
    public Spell(SpellProperties projectileProperties){
        this.properties = projectileProperties;
    }
    public Spell(){
        this.properties = new SpellProperties();
    }

    public Spell(int manaDrain){
        this.properties = new SpellProperties().setManaDrain(manaDrain);
    }

    public void Cast(SpellGroup spellGroup, Entity entity, World world, Vector3d position, Vector3d rotation){
        ExecuteBeforeCast(spellGroup, entity, world, position, rotation);
        ExecuteOnCast(spellGroup, entity, world, position, rotation);
        ExecuteAfterCast(spellGroup, entity, world, position, rotation);
    }
    public void Modify(SpellGroup spellGroup){
        // applies the properties of this spell to the spell group
        spellGroup.getSpellProperties().Change(this);
    }
    public boolean CountsToCast(){
        return false;
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

    public void ExecuteOnDespawn(PlayerEntity owner, World level, MagicProjectile magicProjectile){

    }

    public void ModifyProjectileOnCast(MagicProjectile projectile){

    }

    public boolean hasPayload() {
        return false;
    }

    public int getUses() {
        return uses;
    }

    public Spell setUses(int uses) {
        this.uses = uses;
        return this;
    }

    public int getManaDrain() {
        return properties.getManaDrain();
    }

    public Spell setManaDrain(int manaDrain) {
        properties.setManaDrain(manaDrain);
        return this;
    }

    public float getRechargeTime() {
        return properties.getRechargeTime();
    }

    public Spell setRechargeTime(float rechargeTime) {
        properties.setRechargeTime(rechargeTime);
        return this;
    }

    public float getCastDelay() {
        return properties.getCastDelay();
    }

    public Spell setCastDelay(float castDelay) {
        properties.setCastDelay(castDelay);
        return this;
    }

    public float getSpread() {
        return properties.getSpread();
    }

    public Spell setSpread(float spread) {
        properties.setSpread(spread);
        return this;
    }

    public float getRecoil() {
        return properties.getRecoil();
    }

    public Spell setRecoil(float recoil) {
        properties.setRecoil(recoil);
        return this;
    }

    public float getSpeedMult() {
        return properties.getSpeedMult();
    }

    public Spell setSpeedMult(float speedMult) {
        properties.setSpeedMult(speedMult);
        return this;
    }

    public float getCritChanceBonus() {
        return properties.getCritChanceBonus();
    }

    public Spell setCritChanceBonus(float critChanceBonus) {
        properties.setCritChanceBonus(critChanceBonus);
        return this;
    }

    public float getDamageBonus() {
        return properties.getDamageBonus();
    }

    public Spell setDamageBonus(float damageBonus) {
        properties.setDamageBonus(damageBonus);
        return this;
    }

    public int getLifetime() {
        return properties.getLifetime();
    }

    public Spell setLifetime(int lifetime) {
        properties.setLifetime(lifetime);
        return this;
    }

    public boolean isFriendlyFire() {
        return properties.isFriendlyFire();
    }

    public Spell setFriendlyFire(boolean friendlyFire) {
        properties.setFriendlyFire(friendlyFire);
        return this;
    }

    public int getBounces() {
        return properties.getBounces();
    }

    public Spell setBounces(int bounces) {
        properties.setBounces(bounces);
        return this;
    }

    public int getPierces() {
        return properties.getPierces();
    }

    public Spell setPierces(int pierces) {
        properties.setPierces(pierces);
        return this;
    }

    public boolean isFullPiercing() {
        return properties.isFullPiercing();
    }

    public Spell setFullPiercing(boolean fullPiercing) {
        properties.setFullPiercing(fullPiercing);
        return this;
    }


}
