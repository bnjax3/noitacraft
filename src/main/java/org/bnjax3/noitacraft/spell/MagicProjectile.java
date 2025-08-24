package org.bnjax3.noitacraft.spell;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.wand.SpellGroup;

import javax.annotation.Nullable;

public abstract class MagicProjectile extends ProjectileEntity {
    public ProjectileSpell Spell;
    public int bounces;
    public int lifetime;
    private SpellGroup spellGroup;
    private SpellProperties spellProperties;

    MagicProjectile(EntityType<? extends net.minecraft.entity.projectile.ProjectileEntity> projectileEntity, World world, ProjectileSpell spell, @Nullable SpellGroup spellGroup) {
        super(projectileEntity, world);
        this.spellGroup = spellGroup;
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult hitBlock) {
        super.onHitBlock(hitBlock);
        if (bounces > 0){
            bounce(hitBlock.getDirection());
            bounces--;
        } else {
            Spell.ExecuteOnHit((PlayerEntity) getOwner(),getCommandSenderWorld(), this);
            this.remove();
        }

    }

    @Override
    protected void onHitEntity(EntityRayTraceResult hitEntity) {
        super.onHitEntity(hitEntity);

    }

    public void tick() {
        if (lifetime <= 0){
            Spell.ExecuteOnHit((PlayerEntity) getOwner(),getCommandSenderWorld(),this);
            this.remove();
        }
        super.tick();
        Vector3d deltaMovement = this.getDeltaMovement();
        Spell.ExecuteOnProjectileTickUnshared(this);
        doTickFunctionalities();
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = MathHelper.sqrt(getHorizontalDistanceSqr(deltaMovement));
            this.yRot = (float)(MathHelper.atan2(deltaMovement.x, deltaMovement.z) * (double)(180F / (float)Math.PI));
            this.xRot = (float)(MathHelper.atan2(deltaMovement.y, f) * (double)(180F / (float)Math.PI));
            this.yRotO = this.yRot;
            this.xRotO = this.xRot;
        }
        lifetime--;
    }

    private void doTickFunctionalities() {
        for (Spell spell : spellGroup.Spells){
            spell.ExecuteOnProjectileTick(this);
        }
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

    public void ShootSpell(){
        
    }
    public void setSpellGroup(SpellGroup spellGroup) {
        this.spellGroup = spellGroup;
    }
    public void setSpellProperties(SpellProperties spellProperties){
        this.spellProperties = spellProperties;
        applyProperties();
    }
    private void applyProperties(){
        spellProperties.ChangeByAll(Spell);
        this.bounces = spellProperties.bounces;
        this.lifetime = spellProperties.lifetime;
    }

}
