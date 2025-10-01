package org.bnjax3.noitacraft.spell.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.bnjax3.noitacraft.other.Simplifier;
import org.bnjax3.noitacraft.spell.ProjectileSpell;
import org.bnjax3.noitacraft.spell.Spell;
import org.bnjax3.noitacraft.spell.SpellProperties;
import org.bnjax3.noitacraft.spell.SpellsRegistry;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class MagicProjectile extends ThrowableEntity {
    public ProjectileSpell Spell;
    public int bounces;
    public int lifetime;
    private SpellGroup spellGroup;
    private SpellProperties spellProperties;

    public MagicProjectile(EntityType<? extends ThrowableEntity> entityType, World world){
        super(entityType, world);
        Spell = (ProjectileSpell) SpellsRegistry.DEFAULT_SPELL;
    }
    public MagicProjectile(EntityType<? extends ThrowableEntity> entityType, World world, ProjectileSpell spell) {
        this(entityType, world);
        Spell = spell;
        // para que no se use el metodo de la super que es una cagada
        this.setNoGravity(true);
    }

    public MagicProjectile(EntityType<? extends ThrowableEntity> entityType, double x, double y, double z, World world, ProjectileSpell spell) {
        this(entityType, world, spell);
        this.setPos(x,y,z);
    }
    public MagicProjectile(EntityType<? extends ThrowableEntity> entityType, Vector3d vector3d, World world, ProjectileSpell spell) {
        this(entityType, world, spell);
        this.setPos(vector3d.x, vector3d.y, vector3d.z);
    }

    public MagicProjectile(EntityType<? extends ThrowableEntity> entityType, LivingEntity shooter, World world, ProjectileSpell spell) {
        this(entityType,shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ(), world, spell);
        this.setOwner(shooter);
    }


    @Override
    protected void onHitBlock(BlockRayTraceResult hitBlock) {
        super.onHitBlock(hitBlock);
        if (bounces > 0){
            bounce(hitBlock.getDirection());
            bounces--;
        } else {
            bounce(hitBlock.getDirection());
            Spell.ExecuteOnHit((PlayerEntity) getOwner(),getCommandSenderWorld(), this);
            this.remove();
        }

    }

    @Override
    protected void onHitEntity(EntityRayTraceResult hitEntity) {
        super.onHitEntity(hitEntity);

    }


    @Override
    protected void defineSynchedData() {

    }

    public void tick() {
        if (lifetime <= 0){
            Spell.ExecuteOnHit((PlayerEntity) getOwner(),this.level,this);
            this.remove();
        }
        super.tick();
        Vector3d deltaMovement = this.getDeltaMovement();
        Spell.ExecuteOnProjectileTickUnshared(this);
        doTickFunctionalities();
        // rotate towards deltaMovement
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = MathHelper.sqrt(getHorizontalDistanceSqr(deltaMovement));
            this.yRot = (float)(MathHelper.atan2(deltaMovement.x, deltaMovement.z) * Simplifier.RadToDeg);
            this.xRot = (float)(MathHelper.atan2(deltaMovement.y, f) * Simplifier.RadToDeg);
            this.yRotO = this.yRot;
            this.xRotO = this.xRot;
        }

        // do gravity
        this.setDeltaMovement( deltaMovement.x, deltaMovement.y - (double)this.getGravity(), deltaMovement.z);

        lifetime--;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        Entity entity = this.getOwner();
        return new SSpawnObjectPacket(this, entity == null ? 0 : entity.getId());
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
        this.yRot = (float)(MathHelper.atan2(deltaMovement.x, deltaMovement.z) * Simplifier.RadToDeg);
        this.xRot = (float)(MathHelper.atan2(deltaMovement.y, f) * Simplifier.RadToDeg);
        this.yRotO = this.yRot;
        this.xRotO = this.xRot;
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
