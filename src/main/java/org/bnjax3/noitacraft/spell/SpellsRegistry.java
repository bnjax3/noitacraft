package org.bnjax3.noitacraft.spell;


import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;

import org.bnjax3.noitacraft.entity.ModEntities;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.item.ModItems;
import org.bnjax3.noitacraft.spell.spells.Light;


public class SpellsRegistry {


    public static final ProjectileSpell SPARK_BOLT = new ProjectileSpell(-1,5,1,0,-1,0.1f,0.5f,1,5,3,60,false,0,0.01f, ModEntities.SPARK_BOLT_PROJECTILE);

    // porque sino /summon a cualquier proyectil crashea a la re bosta
    public static final ProjectileSpell DEFAULT_SPELL = new ProjectileSpell(-1,5,1,0,0,0,0.5f,1,0,3,60,false,0,0.1f, ModEntities.SPARK_BOLT_PROJECTILE);

    public static final ProjectileSpell BOUNCING_BURST = new ProjectileSpell(-1,5,-1,0,-1,0.1f,0.5f,0.8f,0,3,100,false,10,0.25f, ModEntities.BOUNCING_BURST_PROJECTILE);

    public static final MulticastSpell DOUBLE_CAST = new MulticastSpell(-1,0,0,0,0,2);
    public static final MulticastSpell TRIPLE_CAST = new MulticastSpell(-1,2,0,0,0,3);
    public static final MulticastSpell QUAD_CAST = new MulticastSpell(-1,5,0,0,0,4);

    public static final Light LIGHT = new Light(10,1);
    public static final Light MORE_LIGHT = new Light(15,2);

    public static final Spell SPARK_BOLT_TRIGGER = new TriggerSpell(SPARK_BOLT,1);
    public static final Spell SPARK_BOLT_TIMER = new TimerSpell(SPARK_BOLT, 1, 40);
}
