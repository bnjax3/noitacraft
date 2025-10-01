package org.bnjax3.noitacraft.spell;


import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;

import org.bnjax3.noitacraft.entity.ModEntities;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.item.ModItems;



public class SpellsRegistry {


    public static final Spell SPARK_BOLT = new ProjectileSpell(-1,5,1,0,-1,0.1f,0.5f,1,5,3,60,false,0,0.01f, ModEntities.SPARK_BOLT_PROJECTILE);

    // porque sino /summon a cualquier proyectil crashea a la re bosta
    public static final Spell DEFAULT_SPELL = new ProjectileSpell(-1,5,1,0,0,0,0.5f,1,0,3,60,false,0,0, ModEntities.SPARK_BOLT_PROJECTILE);
}
