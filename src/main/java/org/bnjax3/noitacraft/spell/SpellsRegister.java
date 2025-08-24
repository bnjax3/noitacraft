package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.entity.ModEntities;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.item.ModItems;
import org.bnjax3.noitacraft.spell.projectiles.SparkBoltProjectile;

import java.util.function.Supplier;

public class SpellsRegister {

    public static final EntityType<SparkBoltProjectile> SPARK_BOLT_PROJECTILE = ModEntities.ENTITY_TYPES.register("spark_bolt", EntityType.Builder.<ArrowEntity>of(SparkBoltProjectile::new, EntityClassification.MISC).sized(0.5F, 0.5F);

    public static final RegistryObject<SpellItem> SPARK_BOLT = registerSpellItem("spark_bolt", new ProjectileSpell(-1,5,1,0,-1,0.1f,0.5f,1,5,3,60,false,0,0.2f,SPARK_BOLT_PROJECTILE));


    @SuppressWarnings("unchecked")
    private static <T extends SpellItem>RegistryObject<T> registerSpellItem(String spellName, Spell spell){
        return (RegistryObject<T>) ModItems.ITEMS.register(spellName, () -> new SpellItem(new Item.Properties().tab(ModItemGroup.SPELLS_GROUP), spell));
    }



    /*
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register( name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.REDSTONE_ADDONS_GROUP)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

     */
}
