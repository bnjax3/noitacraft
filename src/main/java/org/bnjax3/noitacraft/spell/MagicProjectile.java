package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class MagicProjectile extends ProjectileEntity {
    public ProjectileSpell Spell;
    private int bounces;
    private int lifetime;

    MagicProjectile(EntityType<? extends net.minecraft.entity.projectile.ProjectileEntity> projectileEntity, World world, ProjectileSpell spell) {
        super(projectileEntity, world);
        Spell = spell;
        bounces = spell.bounces;
        lifetime = spell.lifetime;
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult hitBlock) {
        super.onHitBlock(hitBlock);
        if (bounces > 0){
            bounce(hitBlock.getDirection());
            bounces--;
        } else {
            Spell.ExecuteOnHit();
            this.remove();
        }

    }

    @Override
    protected void onHitEntity(EntityRayTraceResult hitEntity) {
        super.onHitEntity(hitEntity);

    }
    public void tick() {
        if (lifetime <= 0){
            Spell.ExecuteOnHit();
            this.remove();
        }
        super.tick();
        Vector3d deltaMovement = this.getDeltaMovement();

        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = MathHelper.sqrt(getHorizontalDistanceSqr(deltaMovement));
            this.yRot = (float)(MathHelper.atan2(deltaMovement.x, deltaMovement.z) * (double)(180F / (float)Math.PI));
            this.xRot = (float)(MathHelper.atan2(deltaMovement.y, f) * (double)(180F / (float)Math.PI));
            this.yRotO = this.yRot;
            this.xRotO = this.xRot;
        }
        lifetime--;
    }
    private void bounce(Direction direction){
        // si no anda probablemente sea un problema de hitBlock.getDirection()
        Vector3d deltaMovement = getDeltaMovement();
        if (direction == Direction.UP || direction == Direction.DOWN){
            setDeltaMovement(deltaMovement.x, deltaMovement.y * -1, deltaMovement.z);
        } else if (direction == Direction.EAST || direction == Direction.WEST){
            setDeltaMovement(deltaMovement.x * -1, deltaMovement.y, deltaMovement.z);
        } else if (direction == Direction.NORTH || direction == Direction.SOUTH){
            setDeltaMovement(deltaMovement.x, deltaMovement.y, deltaMovement.z * -1);
        }
        float f = MathHelper.sqrt(getHorizontalDistanceSqr(deltaMovement));
        this.yRot = (float)(MathHelper.atan2(deltaMovement.x, deltaMovement.z) * (double)(180F / (float)Math.PI));
        this.xRot = (float)(MathHelper.atan2(deltaMovement.y, f) * (double)(180F / (float)Math.PI));
        this.yRotO = this.yRot;
        this.xRotO = this.xRot;
    }


}
