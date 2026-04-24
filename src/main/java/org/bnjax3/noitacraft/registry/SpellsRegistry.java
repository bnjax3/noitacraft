package org.bnjax3.noitacraft.registry;


import org.bnjax3.noitacraft.spell.main_classes.*;
import org.bnjax3.noitacraft.spell.spells.modifiers.HeavyShot;
import org.bnjax3.noitacraft.spell.spells.modifiers.Light;
import org.bnjax3.noitacraft.spell.spells.paths.SpiralArc;
import org.bnjax3.noitacraft.spell.spells.projectiles.*;


public class SpellsRegistry {
    /*
    noita player hp = 100
    mc player hp = 20

    im taking this for damage calculations
    (dmg noita / 5 = dmg mc)

     */
    public static final ProjectileSpell SPARK_BOLT = (ProjectileSpell) new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(0.6f)
            .setCritChance(5)
            .setSpeed(1.5f)
            .setGravity(0.025f)
            .setLifetime(20).setKnockback(1)
    ).setDistPlayerFactor(0.15f).setCastDelay(0.05f).setSpread(-1);

    // porque sino /summon a cualquier proyectil crashea a la re bosta
    public static final ProjectileSpell DEFAULT_SPELL = new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(3));

    public static final ProjectileSpell BOUNCING_BURST = (ProjectileSpell) new BouncyBall(5, ModEntities.BOUNCING_BURST_PROJECTILE,
            new ProjectileProperties().setDamage((float)3/5).setGravity(0.025f).setSpeed(1.15f)
                    .setBounces(10)).setSpread(-1).setCastDelay(-0.03f).setLifetime(40).setRecoil(0.05f);

    public static final ProjectileSpell CHAINSAW = (ProjectileSpell) new Chainsaw(1, ModEntities.CHAINSAW_PROJECTILE,
            new ProjectileProperties().setLifetime(0).setSpeed(0).setDamage(12.75f/5)
    ).setRechargeTime(-0.17f).setSpread(6);

    public static final ProjectileSpell DISC = (ProjectileSpell) new Disc(20, ModEntities.DISC_PROJECTILE,
            new ProjectileProperties().setDamage(4).setFriendlyFire(true).setBounces(2)
                    .setSpeed(0.9f).setGravity(0.025f).setLifetime(20)).setCastDelay(0.17f).setSpread(2).setRecoil(0.2f);

    public static final ProjectileSpell SAWBLADE = (ProjectileSpell) new Sawblade(40, ModEntities.SAWBLADE_PROJECTILE,
            new ProjectileProperties().setGravity(0).setLifetime(40).setSpeed(0.5f).setFriendlyFire(true).setBounces(2)
                    .setDamage(62.5f/5)).setCastDelay(0.33f).setSpread(4).setRecoil(0.2f);

    public static final ProjectileSpell BUBBLE = (ProjectileSpell) new Bubble(5, ModEntities.BUBBLE_PROJECTILE,
            new ProjectileProperties().setSpeed(0.5f).setGravity(0).setBounces(20).setLifetime(200/6).setDamage(1)
    ).setSpread(23).setCastDelay(-0.08f);

    public static final ProjectileSpell SPITTER_BOLT = (ProjectileSpell) new Spitter(5, ModEntities.SPITTER_PROJECTILE,
            new ProjectileProperties().setLifetime(54/6).setSpeed(1.2f).setBounces(10).setGravity(0.01f).setDamage(1.5f))
            .setCastDelay(-0.02f).setSpread(6);

    public static final TriggerSpell HOLLOW_EGG = (TriggerSpell) new EggProjectile(ModEntities.EGG_PROJECTILE,
            new ProjectileProperties().setSpeed(0.75f).setGravity(0.3f).setDamage(1)
    ).setCastDelay(-0.2f).setManaDrain(30);

    public static final MulticastSpell DOUBLE_CAST = new MulticastSpell(0,2);
    public static final MulticastSpell TRIPLE_CAST = new MulticastSpell(2,3);
    public static final MulticastSpell QUAD_CAST = new MulticastSpell(5,4);

    public static final MulticastSpell DOUBLE_SCATTER = new MulticastSpell(0,2,10);
    public static final MulticastSpell TRIPLE_SCATTER = new MulticastSpell(1, 3, 20);
    public static final MulticastSpell QUAD_SCATTER = new MulticastSpell(2,4,40);

    public static final ModifierSpell ADD_MANA = new ModifierSpell(new SpellProperties().setManaDrain(-30).setCastDelay(0.17f));

    public static final Light LIGHT = new Light(10,1);
    public static final Light MORE_LIGHT = new Light(15,2);

    public static final Spell SPARK_BOLT_TRIGGER = new TriggerSpell(SPARK_BOLT).setManaDrain(10);
    public static final Spell SPARK_BOLT_TIMER = new TimerSpell(SPARK_BOLT, 10).setManaDrain(10); // ahora si

    public static final ModifierSpell BOUNCES_PLUS = new ModifierSpell(new SpellProperties().setBounces(5));
    public static final ModifierSpell RECHARGE_MINUS = new ModifierSpell(new SpellProperties().setRechargeTime(-0.33f).setCastDelay(-0.17f).setManaDrain(12));
    public static final ModifierSpell SPEED_PLUS = new ModifierSpell(new SpellProperties().setSpeedMult(2.5f).setManaDrain(3));
    public static final ModifierSpell HEAVY_SPREAD = new ModifierSpell(new SpellProperties().setManaDrain(2).setCastDelay(-0.12f).setRechargeTime(-0.25f).setSpread(720));
    public static final ModifierSpell LIFETIME_PLUS = new ModifierSpell(new SpellProperties().setManaDrain(40).setLifetime(40).setCastDelay(0.22f));

    public static final SpiralArc SPIRAL = new SpiralArc(new SpellProperties().setCastDelay(-0.10f).setLifetime(16).setDamageBonus(2.5f));


    public static final ModifierSpell HEAVY_SHOT = new HeavyShot(new SpellProperties()
            .setManaDrain(7).setSpeedMult(0.3f)
            .setDamageBonus(6).setRecoil(50)
            .setCastDelay(0.17f).setKnockbackBonus(2));

    public static final ModifierSpell BLOODLUST = new HeavyShot(new SpellProperties()
            .setRecoil(30).setDamageBonus(32.5f / 5)
            .setSpread(6).setCastDelay(0.13f)
            .setManaDrain(2).setFriendlyFire(true));

    /*
    if i ever need to register spells here

    public static final Map<String, Spell> Spells = new HashMap<String, Spell>(1){{
       put("spark_bolt", SPARK_BOLT);
       put("bouncing_burst", BOUNCING_BURST);
    }};

     */
}
