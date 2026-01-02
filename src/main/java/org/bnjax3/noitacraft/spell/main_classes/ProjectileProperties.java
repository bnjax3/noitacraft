package org.bnjax3.noitacraft.spell.main_classes;

public class ProjectileProperties {
    private float speed = 2;
    private float critChance = 0;
    private float damage = 1;
    private int lifetime = 100; // ticks
    private boolean friendlyFire = false;
    private int bounces = 0;
    private float gravity = 0; // block/tick^2??
    public ProjectileProperties(){

    }
    public ProjectileProperties(float damage){
        this.damage = damage;
    }
    public float getSpeed() {
        return speed;
    }

    public ProjectileProperties setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public float getCritChance() {
        return critChance;
    }

    public ProjectileProperties setCritChance(float critChance) {
        this.critChance = critChance;
        return this;
    }

    public float getDamage() {
        return damage;
    }

    public ProjectileProperties setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public int getLifetime() {
        return lifetime;
    }

    public ProjectileProperties setLifetime(int lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public boolean isFriendlyFire() {
        return friendlyFire;
    }

    public ProjectileProperties setFriendlyFire(boolean friendlyFire) {
        this.friendlyFire = friendlyFire;
        return this;
    }

    public int getBounces() {
        return bounces;
    }

    public ProjectileProperties setBounces(int bounces) {
        this.bounces = bounces;
        return this;
    }

    public float getGravity() {
        return gravity;
    }

    public ProjectileProperties setGravity(float gravity) {
        this.gravity = gravity;
        return this;
    }

}
