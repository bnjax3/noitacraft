package org.bnjax3.noitacraft.spell.projectile;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.bnjax3.noitacraft.other.Simplifier;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.main_classes.Spell;
import org.bnjax3.noitacraft.spell.main_classes.SpellProperties;
import org.bnjax3.noitacraft.spell.SpellsRegistry;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class MagicProjectile extends AbstractArrowEntity {
    public ProjectileSpell Spell;
    public int bouncesLeft;
    public int lifetimeLeft;
    public SpellGroup spellGroup;
    private SpellProperties spellProperties;

    public MagicProjectile(EntityType<? extends AbstractArrowEntity> entityType, World world){
        super(entityType, world);
        Spell = SpellsRegistry.DEFAULT_SPELL;
    }
    public MagicProjectile(EntityType<? extends AbstractArrowEntity> entityType, World world, ProjectileSpell spell) {
        this(entityType, world);
        Spell = spell;
        // para que no se use el metodo de la super que es una cagada
        this.setNoGravity(true);
    }

    public MagicProjectile(EntityType<? extends AbstractArrowEntity> entityType, double x, double y, double z, World world, ProjectileSpell spell) {
        this(entityType, world, spell);
        this.setPos(x,y,z);
    }
    public MagicProjectile(EntityType<? extends AbstractArrowEntity> entityType, Vector3d vector3d, World world, ProjectileSpell spell) {
        this(entityType, world, spell);
        this.setPos(vector3d.x, vector3d.y, vector3d.z);
    }

    public MagicProjectile(EntityType<? extends AbstractArrowEntity> entityType, LivingEntity shooter, World world, ProjectileSpell spell) {
        this(entityType,shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ(), world, spell);
        this.setOwner(shooter);
    }
    @MethodsReturnNonnullByDefault
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult hitBlock) {
        super.onHitBlock(hitBlock);
        if (bouncesLeft > 0){
            bounce(hitBlock.getDirection());
            bouncesLeft--;
        } else {
            Spell.ExecuteOnHit( this, hitBlock);
        }

    }

    @MethodsReturnNonnullByDefault
    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult hitEntity) {
        Entity entity = hitEntity.getEntity();
        Entity owner = this.getOwner();
        DamageSource damagesource;
        float damage = this.Spell.getDamage() + this.spellGroup.getSpellProperties().getDamageBonus();
        if (owner == null) {
            damagesource = DamageSource.arrow(this, this);
        } else {
            damagesource = DamageSource.arrow(this, owner);
            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
        }
        boolean enderman = entity.getType() == EntityType.ENDERMAN;
        int remainingFireTicks = entity.getRemainingFireTicks();
        if (this.isOnFire() && !enderman) {
            entity.setSecondsOnFire(5);
        }
        entity.invulnerableTime = 0;
        if (entity.hurt(damagesource, damage)) {
            if (enderman) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!this.level.isClientSide && this.getPierceLevel() <= 0) {
                    livingentity.setArrowCount(livingentity.getArrowCount() + 1);
                }
                /*
                if (this.knockback > 0) {
                    Vector3d vector3d = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
                    if (vector3d.lengthSqr() > 0.0D) {
                        livingentity.push(vector3d.x, 0.1D, vector3d.z);
                    }
                }
                 */
                this.doPostHurtEffects(livingentity);
                if (livingentity != owner && livingentity instanceof PlayerEntity && owner instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity)owner).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));
                }
                /*
                if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
                    this.piercedAndKilledEntities.add(livingentity);
                }

                if (!this.level.isClientSide && owner instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)owner;

                    if (this.piercedAndKilledEntities != null && this.shotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, this.piercedAndKilledEntities);
                    } else if (!entity.isAlive() && this.shotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, Arrays.asList(entity));
                    }
                }
                 */
            }

            this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.remove();
            }
        } else {
            entity.setRemainingFireTicks(remainingFireTicks);
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.yRot += 180.0F;
            this.yRotO += 180.0F;
            if (!this.level.isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                if (this.pickup == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.remove();
            }
        }
        Spell.ExecuteOnHit( this, hitEntity);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }
    @Override
    public void tick() {
        /*
        if (lifetime <= 0){
            Spell.ExecuteOnDeath((PlayerEntity) getOwner(),this.level,this);
            this.remove();
        }
         */
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
        this.setDeltaMovement( deltaMovement.x, deltaMovement.y - (double)this.Spell.getGravity(), deltaMovement.z);
        lifetimeLeft--;
        super.tick();
    }


    private void doTickFunctionalities() {
        if (spellGroup != null) {
            for (Spell spell : spellGroup.Spells) {
                if (spell != null) {
                    spell.ExecuteOnProjectileTick(this);
                }
            }
        }
    }

    public void bounce(Direction direction){
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
        spellProperties.Change(Spell);
        this.bouncesLeft = spellProperties.getBounces();
        this.lifetimeLeft = spellProperties.getLifetime();
    }


}
