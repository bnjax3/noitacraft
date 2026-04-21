package org.bnjax3.noitacraft.spell.spells.paths;

import org.bnjax3.noitacraft.other.Mather;
import org.bnjax3.noitacraft.spell.main_classes.ModifierSpell;
import org.bnjax3.noitacraft.spell.main_classes.SpellProperties;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class SpiralArc extends ModifierSpell {
    public SpiralArc(SpellProperties properties) {
        super(properties);
    }

    @Override
    public void ExecuteOnProjectileTick(MagicProjectile projectile) {
        float xRot = projectile.xRot + ((float) 3 / projectile.getTicksAlive());
        float yRot = projectile.yRot;
        double h = projectile.getDeltaMovement().length();
        System.out.println("spiral");
        double x = h * Math.sin(yRot * Mather.DegToRad);
        double y = h * Math.sin(xRot * Mather.DegToRad);
        double z = h * Math.cos(xRot * Mather.DegToRad) * Math.cos(yRot * Mather.DegToRad);
        projectile.setDeltaMovement(x, y, z);
    }

    @Override
    public void ModifyProjectileOnCast(MagicProjectile projectile) {
        projectile.setGravity(0);
    }
}
