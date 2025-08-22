package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class ProjectileSpell extends Spell {
    public final int radius;
    public final float speed;
    public final float critChanceBonus;
    public final float damage;
    public final int lifetime; // ticks
    public final boolean friendlyFire;
    public final int bounces;
    public final float gravity; // block/tick
    public final MagicProjectile projectile;

    public ProjectileSpell(int uses, int manaDrain, int castDelay, int rechargeTime, float spread, float recoil, int radius, float speed, float critChanceBonus, float damage, int lifetime, boolean friendlyFire, int bounces, float gravity, MagicProjectile projectile) {
        super(uses, manaDrain, castDelay, rechargeTime, spread, recoil, true);
        this.radius = radius;
        this.speed = speed;
        this.critChanceBonus = critChanceBonus;
        this.damage = damage;
        this.lifetime = lifetime;
        this.friendlyFire = friendlyFire;
        this.bounces = bounces;
        this.gravity = gravity;
        this.projectile = projectile;
    }

    @Override
    public void ExecuteOnCast(SpellGroup spellGroup, PlayerEntity player, World world) {
        Shoot(spellGroup, player, world);
    }

    public void Shoot(SpellGroup spellGroup, PlayerEntity player, World world){
        Vector3d vector3d = player.getLookAngle();

        projectile.shoot(vector3d.x, vector3d.y, vector3d.z, speed, Spread);
    }

    public void ExecuteOnHit(){

    }
}

