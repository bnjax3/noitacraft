package org.bnjax3.noitacraft.wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellGroup {
    public final ArrayList<Spell> Casted;
    public final ArrayList<Spell> Modifiers;
    public final ArrayList<Spell> Multicasts;
    public SpellGroup(ArrayList<Spell> casted, ArrayList<Spell> modifiers, ArrayList<Spell> multicasts) {
        Casted = casted;
        Modifiers = modifiers;
        Multicasts = multicasts;
    }

    public void Cast(PlayerEntity player, ItemStack stack, World world){

    }
}
