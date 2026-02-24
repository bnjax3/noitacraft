package org.bnjax3.noitacraft.damageSources;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

import javax.annotation.Nullable;

public class DamageSources {
    public static GenericSpell genericSpell(MagicProjectile magicProjectile, @Nullable Entity owner) {
        return new GenericSpell("genericSpell", magicProjectile, owner);
    }
}
