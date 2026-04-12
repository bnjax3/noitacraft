package org.bnjax3.noitacraft.registry;


import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import org.bnjax3.noitacraft.spell.main_classes.*;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.spell.spells.modifiers.HeavyShot;
import org.bnjax3.noitacraft.spell.spells.modifiers.Light;
import org.bnjax3.noitacraft.spell.spells.projectiles.SparkBolt;

import javax.annotation.Nullable;


public class SpellsRegistry {
    /*
    noita player hp = 100
    mc player hp = 20

    im taking this for damage calculations
    (dmg noita / 5 = dmg mc)

     */
    public static final ProjectileSpell SPARK_BOLT = (ProjectileSpell) new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(0.6f)
            .setCritChance(5)
            .setSpeed(1.25f)
            .setGravity(0.04f)
            .setLifetime(20)
    ).setCastDelay(0.05f).setSpread(-1);

    // porque sino /summon a cualquier proyectil crashea a la re bosta
    public static final ProjectileSpell DEFAULT_SPELL = new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(3)
            .setSpeed(2)
    );

    public static final ProjectileSpell BOUNCING_BURST = new ProjectileSpell(5, ModEntities.BOUNCING_BURST_PROJECTILE, new ProjectileProperties());

    public static final MulticastSpell DOUBLE_CAST = new MulticastSpell(0,2);
    public static final MulticastSpell TRIPLE_CAST = new MulticastSpell(2,3);
    public static final MulticastSpell QUAD_CAST = new MulticastSpell(5,4);

    public static final MulticastSpell DOUBLE_SCATTER = new MulticastSpell(0,2,10);
    public static final MulticastSpell TRIPLE_SCATTER = new MulticastSpell(1, 3, 20);
    public static final MulticastSpell QUAD_SCATTER = new MulticastSpell(2,4,40);

    public static final Light LIGHT = new Light(10,1);
    public static final Light MORE_LIGHT = new Light(15,2);

    public static final Spell SPARK_BOLT_TRIGGER = new TriggerSpell(SPARK_BOLT).setManaDrain(10);
    public static final Spell SPARK_BOLT_TIMER = new TimerSpell(SPARK_BOLT, 10).setManaDrain(10); // ahora si

    public static final ModifierSpell BOUNCES_PLUS = new ModifierSpell(new SpellProperties().setBounces(5));
    public static final ModifierSpell RECHARGE_MINUS = new ModifierSpell(new SpellProperties().setRechargeTime(-0.33f).setCastDelay(-0.17f).setManaDrain(12));
    public static final ModifierSpell SPEED_PLUS = new ModifierSpell(new SpellProperties().setSpeedMult(2.5f).setManaDrain(3));
    public static final ModifierSpell HEAVY_SPREAD = new ModifierSpell(new SpellProperties().setManaDrain(2).setCastDelay(-0.12f).setRechargeTime(-0.25f).setSpread(360));



    public static final ModifierSpell HEAVY_SHOT = new HeavyShot();
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
