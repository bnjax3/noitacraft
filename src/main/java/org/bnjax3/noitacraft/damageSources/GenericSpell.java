package org.bnjax3.noitacraft.damageSources;

import net.minecraft.entity.Entity;
import net.minecraft.util.IndirectEntityDamageSource;

import javax.annotation.Nullable;

public class GenericSpell extends IndirectEntityDamageSource {
    public GenericSpell(String string, Entity entity, @Nullable Entity shooter) {
        super(string, entity, shooter);
        this.setProjectile();
    }
}
