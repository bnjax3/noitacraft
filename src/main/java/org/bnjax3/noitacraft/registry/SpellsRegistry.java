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

    public static final ProjectileSpell SPARK_BOLT = (ProjectileSpell) new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(3)
            .setCritChance(5)
            .setSpeed(2)
            .setGravity(0.04f)
    ).setCastDelay(0.05f).setSpread(-1);

    // porque sino /summon a cualquier proyectil crashea a la re bosta
    public static final ProjectileSpell DEFAULT_SPELL = new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(3)
            .setSpeed(2)
    );

    public static final ProjectileSpell BOUNCING_BURST = new ProjectileSpell(5, ModEntities.BOUNCING_BURST_PROJECTILE, new ProjectileProperties());

    public static final MulticastSpell DOUBLE_CAST = new MulticastSpell(0,2);
    public static final MulticastSpell TRIPLE_CAST = new MulticastSpell(2,3);
    public static final MulticastSpell QUAD_CAST = new MulticastSpell(5,4);

    public static final Light LIGHT = new Light(10,1);
    public static final Light MORE_LIGHT = new Light(15,2);

    public static final Spell SPARK_BOLT_TRIGGER = new TriggerSpell(SPARK_BOLT).setManaDrain(10);
    public static final Spell SPARK_BOLT_TIMER = new TimerSpell(SPARK_BOLT, 40).setManaDrain(10);

    public static final ModifierSpell BOUNCES_PLUS = new ModifierSpell(new SpellProperties().setBounces(5));

    public static final ModifierSpell HEAVY_SHOT = new HeavyShot();
    /*
    if i ever need to register spells here

    public static final Map<String, Spell> Spells = new HashMap<String, Spell>(1){{
       put("spark_bolt", SPARK_BOLT);
       put("bouncing_burst", BOUNCING_BURST);
    }};

     */
}
