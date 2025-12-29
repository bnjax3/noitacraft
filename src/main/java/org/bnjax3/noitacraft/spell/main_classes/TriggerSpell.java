package org.bnjax3.noitacraft.spell.main_classes;

import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.RegistryObject;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class TriggerSpell extends PayloadSpell {

    public TriggerSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties, int count){
        super(projectileRegistryObject, projectileProperties, count);
    }
    public TriggerSpell(RegistryObject<? extends EntityType<? extends MagicProjectile>>  projectileRegistryObject, ProjectileProperties projectileProperties) {
        this(projectileRegistryObject, projectileProperties, 1);
    }
    public TriggerSpell(ProjectileSpell spell, int count){
        this(spell.projectileRegistryObject, spell.projectileProperties, count);
    }
    public TriggerSpell(ProjectileSpell spell){
        this(spell.projectileRegistryObject, spell.projectileProperties, 1);
    }
    public TriggerSpell(TriggerSpell spell, SpellGroup payload){
        this(spell.projectileRegistryObject, spell.projectileProperties, spell.count);
        this.payload = payload;
    }

    @Override
    public void ExecuteOnHit(MagicProjectile magicProjectile, RayTraceResult rayTraceResult) {
        if (rayTraceResult instanceof BlockRayTraceResult){
            magicProjectile.bounce(((BlockRayTraceResult) rayTraceResult).getDirection());
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
        if (rayTraceResult instanceof EntityRayTraceResult){
            CastPayload(magicProjectile);
            magicProjectile.remove();
        }
    }
}
