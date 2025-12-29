package org.bnjax3.noitacraft.spell;


import org.bnjax3.noitacraft.entity.ModEntities;
import org.bnjax3.noitacraft.spell.main_classes.*;
import org.bnjax3.noitacraft.spell.spells.Light;
import org.bnjax3.noitacraft.spell.spells.SparkBolt;


public class SpellsRegistry {


    public static final ProjectileSpell SPARK_BOLT = new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(3).setCritChance(15));

    // porque sino /summon a cualquier proyectil crashea a la re bosta
    public static final ProjectileSpell DEFAULT_SPELL = new SparkBolt(5, ModEntities.SPARK_BOLT_PROJECTILE, new ProjectileProperties(3)
            .setCritChance(15)
            .setSpeed(80)

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

    /*
    if i ever need to register spells here

    public static final Map<String, Spell> Spells = new HashMap<String, Spell>(1){{
       put("spark_bolt", SPARK_BOLT);
       put("bouncing_burst", BOUNCING_BURST);
    }};

     */
}
