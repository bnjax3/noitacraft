package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.entity.ModEntities;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.item.ModItems;
import org.bnjax3.noitacraft.spell.projectiles.SparkBoltProjectile;
import org.lwjgl.system.CallbackI;


public class SpellsRegistry {

    public static final RegistryObject<EntityType<MagicProjectile>> SPARK_BOLT_PROJECTILE = ModEntities.ENTITY_TYPES.register("spark_bolt", () -> EntityType.Builder.<MagicProjectile>of(MagicProjectile::new, EntityClassification.MISC).sized(0.5f,0.5f).build("spark_bolt"));

    public static final Spell SPARK_BOLT = new ProjectileSpell(-1,5,1,0,-1,0.1f,0.5f,1,5,3,60,false,0,0.01f, SPARK_BOLT_PROJECTILE.get());

    public static final RegistryObject<SpellItem> SPARK_BOLT_ITEM = registerSpellItem("spark_bolt", SPARK_BOLT);









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
