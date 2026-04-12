package org.bnjax3.noitacraft.other;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.item.SpellItem;

public abstract class Utils {
    // shit name i know
    public static final int UNIVERSAL_SLOT_PIXEL_SEPARATION = 18; // USPS for shrot
    private static final char charForFillingThatIMayChange = ' '; // CFFTIMC for short

    public static TranslationTextComponent addCharUntilLenghtX(TranslationTextComponent textComponent, int lenght, char filler){
        for (int i = 0;  i < lenght - textComponent.getString().length(); i++){
            textComponent.append(String.valueOf(filler));
        }
        return textComponent;
    }

    public static TranslationTextComponent FormatTooltipData(String resourceLocation, boolean data){
        TranslationTextComponent text = new TranslationTextComponent(resourceLocation);
        return (TranslationTextComponent) addCharUntilLenghtX(text, 20, charForFillingThatIMayChange).append(String.valueOf(data));
    }
    public static TranslationTextComponent FormatTooltipData(String resourceLocation, int data){
        TranslationTextComponent text = new TranslationTextComponent(resourceLocation);
        return (TranslationTextComponent) addCharUntilLenghtX(text, 20, charForFillingThatIMayChange).append(String.valueOf(data));
    }
    public static TranslationTextComponent FormatTooltipData(String resourceLocation, float data){
        TranslationTextComponent text = new TranslationTextComponent(resourceLocation);
        return (TranslationTextComponent) addCharUntilLenghtX(text, 20, charForFillingThatIMayChange).append(String.valueOf(data));
    }
    public static TranslationTextComponent FormatTooltipData(String resourceLocation, String data){
        TranslationTextComponent text = new TranslationTextComponent(resourceLocation);
        return (TranslationTextComponent) addCharUntilLenghtX(text, 20, charForFillingThatIMayChange).append(data);
    }
    public static TranslationTextComponent FormatSpellDescription(SpellItem item){
        String resourceLocation = "tooltip.noitacraft." + item.getRegistryName().getPath() + "_description";
        TranslationTextComponent text = new TranslationTextComponent(resourceLocation);
        return text;
    }

    // despues hay que anadir la funcionalidad del mana aca
    public static CompoundNBT toNBT(SpellItem[] spellItems) {
        CompoundNBT nbt = new CompoundNBT();
        ListNBT list = new ListNBT();
        for(SpellItem spellItem : spellItems){
            if (spellItem != null) {
                list.add(StringNBT.valueOf(spellItem.spellName));
            } else {
                list.add(StringNBT.valueOf("null"));
            }
        }
        nbt.put("SpellItems", list);
        return nbt;
    }
    // y aca tmb
    public static SpellItem[] fromNBT(CompoundNBT nbt) {
        ListNBT spellsNBT = nbt.getList("SpellItems", 8);
        SpellItem[] spellItems = new SpellItem[spellsNBT.size()];
        for (int i = 0; i < spellsNBT.size(); i++){
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Noitacraft.MOD_ID, spellsNBT.getString(i)));
            if (item != null){
                if (item instanceof SpellItem){
                    spellItems[i] = (SpellItem) item;
                }
            } else {
                spellItems[i] = null;
            }
        }
        return spellItems;
    }

    public static int secsToTicks(double secs){
        // if the decimal part of the seconds is less or equal to 0.01 seconds, ignore it
        // if the decimal part is higher, round up
        // 1 tick --- 1/20 sec or 0.05f sec
        int whole = (int) Math.floor(secs * 20);
        if ((secs * 20) - whole <= 0.01f) {
            return whole;
        } else {
            return whole + 1;
        }
    }
}
