package org.bnjax3.noitacraft.spell;


import org.bnjax3.noitacraft.entity.ModEntities;
import org.bnjax3.noitacraft.spell.main_classes.*;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.spell.spells.Light;

import java.util.HashMap;
import java.util.Map;


public class SpellsRegistry {


    public static final ProjectileSpell SPARK_BOLT = new ProjectileSpell(-1,5,0.05f,0,-1,0.1f,0.5f,1,5,3,60,false,0,0.01f, ModEntities.SPARK_BOLT_PROJECTILE);

    // porque sino /summon a cualquier proyectil crashea a la re bosta
    public static final ProjectileSpell DEFAULT_SPELL = new ProjectileSpell(-1,5,0.05f,0,0,0,0.5f,1,0,3,60,false,0,0.1f, ModEntities.SPARK_BOLT_PROJECTILE);

    public static final ProjectileSpell BOUNCING_BURST = new ProjectileSpell(-1,5,-0.03f,0,-1,0.1f,0.5f,0.8f,0,3,100,false,10,0.25f, ModEntities.BOUNCING_BURST_PROJECTILE);

    public static final MulticastSpell DOUBLE_CAST = new MulticastSpell(-1,0,0,0,0,2);
    public static final MulticastSpell TRIPLE_CAST = new MulticastSpell(-1,2,0,0,0,3);
    public static final MulticastSpell QUAD_CAST = new MulticastSpell(-1,5,0,0,0,4);

    public static final Light LIGHT = new Light(10,1);
    public static final Light MORE_LIGHT = new Light(15,2);

    public static final Spell SPARK_BOLT_TRIGGER = new TriggerSpell(SPARK_BOLT,1);
    public static final Spell SPARK_BOLT_TIMER = new TimerSpell(SPARK_BOLT, 1, 40);

    /*
    if i ever need to register spells here

    public static final Map<String, Spell> Spells = new HashMap<String, Spell>(1){{
       put("spark_bolt", SPARK_BOLT);
       put("bouncing_burst", BOUNCING_BURST);
    }};

     */
}
