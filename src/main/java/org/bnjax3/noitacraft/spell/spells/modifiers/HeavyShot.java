package org.bnjax3.noitacraft.spell.spells.modifiers;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import org.bnjax3.noitacraft.spell.main_classes.ModifierSpell;
import org.bnjax3.noitacraft.spell.main_classes.SpellProperties;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class HeavyShot extends ModifierSpell {
    public HeavyShot(SpellProperties properties) {
        super(properties);
    }


    @Override
    public void ExecuteOnProjectileTick(MagicProjectile projectile) {
        Vector3d vector3d = projectile.getDeltaMovement();
        double d3 = vector3d.x;
        double d4 = vector3d.y;
        double d0 = vector3d.z;
        for(int i = 0; i < 4; ++i) {
            projectile.level.addParticle(ParticleTypes.DAMAGE_INDICATOR,
                    projectile.getX() + d3 * (double)i / 4.0D,
                    projectile.getY() + d4 * (double)i / 4.0D,
                    projectile.getZ() + d0 * (double)i / 4.0D,
                    -d3, -d4 + 0.2D, -d0);

        }
    }
}
