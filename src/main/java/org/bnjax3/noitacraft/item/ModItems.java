package org.bnjax3.noitacraft.item;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.spell.Spell;
import org.bnjax3.noitacraft.spell.SpellItem;
import org.bnjax3.noitacraft.spell.SpellsRegistry;
import org.bnjax3.noitacraft.wand.Wand;
import org.bnjax3.noitacraft.wand.WandItem;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Noitacraft.MOD_ID);


    // -----------------------------------------  REGULAR ITEMS  ------------------------------------------------------

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().tab(ModItemGroup.NOITACRAFT_GROUP)));


    // -----------------------------------------  WANDITEMS  ----------------------------------------------------------

    public static final RegistryObject<WandItem> STARTER_WAND = registerWand("starter_wand", new Wand(false, 1,4, 9, 100, 30, 3, 0, 1 ));

    public static final RegistryObject<WandItem> BOMB_WAND = registerWand("bomb_wand", new Wand(true, 1,2, 2, 100, 10, 1, 0, 1 ));







    // -----------------------------------------  SPELLITEMS -----------------------------------------------------------

    public static final RegistryObject<SpellItem> SPARK_BOLT_ITEM = registerSpellItem("spark_bolt", SpellsRegistry.SPARK_BOLT);





    // ----------------------------------------- FUNCTIONS -----------------------------------------------------------

    @SuppressWarnings("unchecked")
    private static <T extends WandItem>RegistryObject<T> registerWand(String name, Wand wand){
        return (RegistryObject<T>) ITEMS.register(name, () -> new WandItem(new Item.Properties().stacksTo(1).tab(ModItemGroup.WANDS_GROUP), wand));
    }

    @SuppressWarnings("unchecked")
    private static <T extends SpellItem>RegistryObject<T> registerSpellItem(String spellName, Spell spell){
        return (RegistryObject<T>) ITEMS.register(spellName, () -> new SpellItem(new Item.Properties().tab(ModItemGroup.SPELLS_GROUP), spell));
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}