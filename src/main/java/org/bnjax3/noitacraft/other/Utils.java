package org.bnjax3.noitacraft.other;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class Utils {
    // shit name i know
    private static char charForFillingThatIMayChange = ' '; // CFFTIMC for short
    public static String addCharUntilLenghtX(String string, int lenght, char filler){
        String toReturn = string;
        for (int i = 0;  i < lenght - string.length(); i++){
            toReturn += filler;
        }
        return toReturn;
    }
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
}
