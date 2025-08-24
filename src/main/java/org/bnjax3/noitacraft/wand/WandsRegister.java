package org.bnjax3.noitacraft.wand;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.item.ModItemGroup;
import org.bnjax3.noitacraft.item.ModItems;
import org.bnjax3.noitacraft.spell.SpellItem;

public class WandsRegister {

    public static final RegistryObject<WandItem> STARTER_WAND = registerWand("starter_wand", new Wand(false, 1,4, 9, 100, 30, 3, 0, 1 ));
    public static final RegistryObject<WandItem> BOMB_WAND = registerWand("bomb_wand", new Wand(true, 1,2, 2, 100, 10, 1, 0, 1 ));

    @SuppressWarnings("unchecked")
    private static <T extends WandItem>RegistryObject<T> registerWand(String name, Wand wand){
        return (RegistryObject<T>) ModItems.ITEMS.register(name, () -> new WandItem(new Item.Properties().tab(ModItemGroup.WANDS_GROUP), wand));
    }
}






























