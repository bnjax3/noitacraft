package org.bnjax3.noitacraft.spell.spells.paths;

import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import org.bnjax3.noitacraft.other.Simplifier;
import org.bnjax3.noitacraft.spell.main_classes.ModifierSpell;
import org.bnjax3.noitacraft.spell.main_classes.SpellProperties;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;
import org.bnjax3.noitacraft.wand.SpellGroup;

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
        double x = h * Math.sin(yRot * Simplifier.DegToRad);
        double y = h * Math.sin(xRot * Simplifier.DegToRad);
        double z = h * Math.cos(xRot * Simplifier.DegToRad) * Math.cos(yRot * Simplifier.DegToRad);
        projectile.setDeltaMovement(x, y, z);
    }

    @Override
    public void ModifyProjectileOnCast(MagicProjectile projectile) {
        projectile.setGravity(0);
    }
}
