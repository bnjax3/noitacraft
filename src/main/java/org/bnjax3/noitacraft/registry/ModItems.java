package org.bnjax3.noitacraft.registry;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.item.SpellItem;
import org.bnjax3.noitacraft.spell.main_classes.Spell;
import org.bnjax3.noitacraft.wand.Wand;
import org.bnjax3.noitacraft.wand.WandItem;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Noitacraft.MOD_ID);


    // -----------------------------------------  REGULAR ITEMS  ------------------------------------------------------

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().tab(ModItemGroup.NOITACRAFT_GROUP)));


    // -----------------------------------------  WANDITEMS  ----------------------------------------------------------

    public static final RegistryObject<WandItem> STARTER_WAND = registerWand("starter_wand", new Wand(false, 1,0.2f, 0.45f, 100, 30, 3, 0, 1 ));

    public static final RegistryObject<WandItem> BOMB_WAND = registerWand("bomb_wand", new Wand(true, 1,0.1f, 0.1f, 100, 10, 1, 0, 1 ));

    public static final RegistryObject<WandItem> WAND_01 = registerWand("wand_01", new Wand(true, 1, 0.03f, 0.45f, 220, 45, 7, 8, 1.03f));

    public static final RegistryObject<WandItem> WAND_02 = registerWand("wand_02", new Wand(true, 1, 0.03f, 0.42f, 250, 53, 5, 2, 0.98f));

    public static final RegistryObject<WandItem> WAND_03 = registerWand("wand_03", new Wand(true, 1, 0.03f, 1.5f, 180, 54, 6, -3, 0.99f));

    public static final RegistryObject<WandItem> WAND_04 = registerWand("wand_04", new Wand(false, 1, 0.38f, 0.27f, 190, 51, 3, -1, 0.93f));

    public static final RegistryObject<WandItem> WAND_05 = registerWand("wand_05", new Wand(true, 1, 0.10f, 0.7f, 210, 52, 5, 6, 0.82f));

    public static final RegistryObject<WandItem> WAND_06 = registerWand("wand_06", new Wand(false, 1, 0.22f, 0.38f, 250, 49, 6, 3, 0.99f));

    public static final RegistryObject<WandItem> WAND_07 = registerWand("wand_07", new Wand(false, 1, 0.43f, 0.67f, 170, 55, 3, 1, 0.96f));

    public static final RegistryObject<WandItem> WAND_08 = registerWand("wand_08", new Wand(true, 1, 0.17f, 0.43f, 160, 52, 7, 2, 0.99f));

    public static final RegistryObject<WandItem> WAND_09 = registerWand("wand_09", new Wand(true, 1, 0.05f, 0.82f, 160, 46, 6, 3, 0.85f));

    public static final RegistryObject<WandItem> WAND_10 = registerWand("wand_10", new Wand(false, 1, 0.05f, 0.43f, 210, 46, 3, -1, 1.06f));

    public static final RegistryObject<WandItem> WAND_11 = registerWand("wand_11", new Wand(false, 1, 0.33f, 0.43f, 250, 50, 2, -2, 1.02f));

    public static final RegistryObject<WandItem> WAND_12 = registerWand("wand_12", new Wand(false, 1, 0.23f, 0.83f, 250, 49, 6, -2, 1.00f));

    public static final RegistryObject<WandItem> WAND_13 = registerWand("wand_13", new Wand(false, 1, 0.07f, 0.75f, 220, 51, 5, 1, 0.97f));

    public static final RegistryObject<WandItem> WAND_14 = registerWand("wand_14", new Wand(false, 1, 0.10f, 0.20f, 210, 50, 2, 1, 0.99f));

    public static final RegistryObject<WandItem> WAND_15 = registerWand("wand_15", new Wand(true, 1, 0.40f, 0.88f, 170, 49, 11, 1, 0.90f));

    public static final RegistryObject<WandItem> WAND_16 = registerWand("wand_16", new Wand(true, 1, 0.43f, 0.30f, 750, 10, 9, -3, 1.08f));

    public static final RegistryObject<WandItem> WAND_17 = registerWand("wand_17", new Wand(true, 1, 0.03f, 0.23f, 190, 52, 6, 5, 0.99f));




    // -----------------------------------------  SPELLITEMS -----------------------------------------------------------

    public static final RegistryObject<SpellItem> SPARK_BOLT_ITEM = registerSpellItem("spark_bolt", SpellsRegistry.SPARK_BOLT);
    public static final RegistryObject<SpellItem> BOUNCING_BURST_ITEM = registerSpellItem("bouncing_burst", SpellsRegistry.BOUNCING_BURST);
    public static final RegistryObject<SpellItem> CHAINSAW_ITEM = registerSpellItem("chainsaw", SpellsRegistry.CHAINSAW);
    public static final RegistryObject<SpellItem> DISC_ITEM = registerSpellItem("disc", SpellsRegistry.DISC);
    public static final RegistryObject<SpellItem> SAWBLADE_ITEM = registerSpellItem("sawblade", SpellsRegistry.SAWBLADE);
    public static final RegistryObject<SpellItem> SPITTER_BOLT_ITEM = registerSpellItem("spitter_bolt", SpellsRegistry.SPITTER_BOLT);
    public static final RegistryObject<SpellItem> BUBBLE_ITEM = registerSpellItem("bubble", SpellsRegistry.BUBBLE);
    public static final RegistryObject<SpellItem> HOLLOW_EGG_ITEM = registerSpellItem("hollow_egg", SpellsRegistry.HOLLOW_EGG);

    public static final RegistryObject<SpellItem> DOUBLE_CAST_ITEM = registerSpellItem("double_cast", SpellsRegistry.DOUBLE_CAST);
    public static final RegistryObject<SpellItem> TRIPLE_CAST_ITEM = registerSpellItem("triple_cast", SpellsRegistry.TRIPLE_CAST);
    public static final RegistryObject<SpellItem> QUAD_CAST_ITEM = registerSpellItem("quad_cast", SpellsRegistry.QUAD_CAST);

    public static final RegistryObject<SpellItem> DOUBLE_SCATTER_ITEM = registerSpellItem("double_scatter", SpellsRegistry.DOUBLE_SCATTER);
    public static final RegistryObject<SpellItem> TRIPLE_SCATTER_ITEM = registerSpellItem("triple_scatter", SpellsRegistry.TRIPLE_SCATTER);
    public static final RegistryObject<SpellItem> QUAD_SCATTER_ITEM = registerSpellItem("quad_scatter", SpellsRegistry.QUAD_SCATTER);

    public static final RegistryObject<SpellItem> LIGHT_ITEM = registerSpellItem("light", SpellsRegistry.LIGHT);
    public static final RegistryObject<SpellItem> MORE_LIGHT_ITEM = registerSpellItem("more_light", SpellsRegistry.MORE_LIGHT);

    public static final RegistryObject<SpellItem> SPARK_BOLT_TRIGGER_ITEM = registerSpellItem("spark_bolt_trigger", SpellsRegistry.SPARK_BOLT_TRIGGER);
    public static final RegistryObject<SpellItem> SPARK_BOLT_TIMER_ITEM = registerSpellItem("spark_bolt_timer", SpellsRegistry.SPARK_BOLT_TIMER);

    public static final RegistryObject<SpellItem> BOUNCES_PLUS_ITEM = registerSpellItem("bounces_plus", SpellsRegistry.BOUNCES_PLUS);
    public static final RegistryObject<SpellItem> RECHARGE_MINUS_ITEM = registerSpellItem("recharge_minus", SpellsRegistry.RECHARGE_MINUS);
    public static final RegistryObject<SpellItem> SPEED_PLUS_ITEM = registerSpellItem("speed_plus", SpellsRegistry.SPEED_PLUS);
    public static final RegistryObject<SpellItem> LIFETIME_PLUS = registerSpellItem("lifetime_plus", SpellsRegistry.LIFETIME_PLUS);
    public static final RegistryObject<SpellItem> HEAVY_SPREAD_ITEM = registerSpellItem("heavy_spread", SpellsRegistry.HEAVY_SPREAD);

    public static final RegistryObject<SpellItem> HEAVY_SHOT_ITEM = registerSpellItem("heavy_shot", SpellsRegistry.HEAVY_SHOT);
    public static final RegistryObject<SpellItem> BLOODLUST_ITEM = registerSpellItem("bloodlust", SpellsRegistry.BLOODLUST);

    public static final RegistryObject<SpellItem> SPIRAL_ITEM = registerSpellItem("spiral_arc", SpellsRegistry.SPIRAL);

    // ----------------------------------------- FUNCTIONS -----------------------------------------------------------

    @SuppressWarnings("unchecked")
    private static <T extends WandItem>RegistryObject<T> registerWand(String name, Wand wand){
        return (RegistryObject<T>) ITEMS.register(name, () -> new WandItem(wand));
    }

    @SuppressWarnings("unchecked")
    private static <T extends SpellItem>RegistryObject<T> registerSpellItem(String spellName, Spell spell){
        return (RegistryObject<T>) ITEMS.register(spellName, () -> new SpellItem(new Item.Properties().stacksTo(1).tab(ModItemGroup.SPELLS_GROUP), spell, spellName));
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}