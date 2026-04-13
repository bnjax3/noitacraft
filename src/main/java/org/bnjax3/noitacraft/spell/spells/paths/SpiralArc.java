package org.bnjax3.noitacraft.spell.spells.paths;

import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import org.bnjax3.noitacraft.spell.main_classes.ModifierSpell;
import org.bnjax3.noitacraft.spell.main_classes.SpellProperties;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

public class SpiralArc extends ModifierSpell {
    public SpiralArc(SpellProperties properties) {
        super(properties);
    }

    @Override
    public void ExecuteOnProjectileTick(MagicProjectile projectile) {
        projectile.xRot += 3;
        System.out.println("spiral");
    }
}
