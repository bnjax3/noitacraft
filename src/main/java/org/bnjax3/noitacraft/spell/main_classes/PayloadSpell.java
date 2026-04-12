package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

import javax.annotation.Nullable;

public class PayloadSpell extends ProjectileSpell{
    public @Nullable SpellGroup payload;
    public final int count;

    public PayloadSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties, int count){
        super(projectileRegistryObject, projectileProperties);
        this.count = count;
    }
    public PayloadSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties) {
        this(projectileRegistryObject, projectileProperties, 1);
    }

    public void CastPayload(MagicProjectile projectile){
        if (payload == null) {
            System.out.println("Payload is null");
            return;
        }
        payload.Cast(projectile.getOwner(), projectile.getCommandSenderWorld(), projectile.position(), projectile.getDeltaMovement());
    }

    @Override
    public String toString() {
            return "PayloadSpell "  + super.toString() + " {" +
                    "payload=" + payload +
                    ", count=" + count +
                    '}';

    }

    @Override
    public boolean hasPayload() {
        // System.out.println(payload);
        return (payload != null  && !payload.isEmpty());
    }
}
