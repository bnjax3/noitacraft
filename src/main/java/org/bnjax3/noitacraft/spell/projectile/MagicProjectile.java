package org.bnjax3.noitacraft.spell.projectile;


import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.bnjax3.noitacraft.damageSources.DamageSources;
import org.bnjax3.noitacraft.damageSources.GenericSpell;
import org.bnjax3.noitacraft.other.Simplifier;
import org.bnjax3.noitacraft.spell.main_classes.ProjectileSpell;
import org.bnjax3.noitacraft.spell.main_classes.Spell;
import org.bnjax3.noitacraft.registry.SpellsRegistry;
import org.bnjax3.noitacraft.wand.SpellGroup;

public class MagicProjectile extends AbstractArrowEntity {
    public ProjectileSpell Spell;
    private int bouncesLeft;
    private int lifetimeLeft;
    public SpellGroup spellGroup;

    public MagicProjectile(EntityType<? extends AbstractArrowEntity> entityType, World world){
        super(entityType, world);
        Spell = SpellsRegistry.DEFAULT_SPELL;
        // para que no se use el metodo de la super que es una cagada
        this.setNoGravity(true);
    }
    public MagicProjectile(EntityType<? extends AbstractArrowEntity> entityType, World world, ProjectileSpell spell) {
        this(entityType, world);
        Spell = spell;
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
    public void tick() {
        super.tick();
        if (this.level.isClientSide){
            return;
        }

        if (lifetimeLeft <= 0){
            Spell.ExecuteOnDeath((PlayerEntity) getOwner(),this.level,this);
            this.remove();
        }
        Vector3d deltaMovement = this.getDeltaMovement();
        Spell.ExecuteOnProjectileTickUnshared(this);
        doTickFunctionalities();
        // do gravity
        this.setDeltaMovement( deltaMovement.x, deltaMovement.y - (double)this.Spell.getGravity(), deltaMovement.z);
        lifetimeLeft--;
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

    @Override
    public void onHitEntity(EntityRayTraceResult hitEntity) {
        Entity entity = hitEntity.getEntity();
        Entity owner = this.getOwner();
        DamageSource damagesource;
        float damage;
        if (entity.level.isClientSide){ return;}
        // i should do crit processing later
        if (spellGroup != null) {
            damage = this.Spell.getDamage() + this.spellGroup.getSpellProperties().getDamageBonus();
        } else {
            damage = this.Spell.getDamage();
        }
        if (owner == null) {
            damagesource = DamageSources.genericSpell(this, null);
        } else {
            damagesource = DamageSources.genericSpell(this, owner);
            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
        }
        System.out.println("Projectile DamageSource : " + damagesource);
        System.out.println("Projectile BypassInvul : " + damagesource.isBypassInvul());
        boolean enderman = entity.getType() == EntityType.ENDERMAN;
        int remainingFireTicks = entity.getRemainingFireTicks();
        if (this.isOnFire() && !enderman) {
            entity.setSecondsOnFire(5);
        }
        if (entity.hurt(damagesource, damage)) {
            entity.invulnerableTime = 0;
            if (enderman) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;

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

            }

            this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.spellGroup.getSpellProperties().getPierces() <= 0) {
                this.remove();
            }
        } else {
            entity.setRemainingFireTicks(remainingFireTicks);
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.yRot += 180.0F;
            this.yRotO += 180.0F;
        }
        Spell.ExecuteOnHit( this, hitEntity);
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult hitBlock) {
        if (this.level.isClientSide){ return;}
        BlockState blockstate = this.level.getBlockState(hitBlock.getBlockPos());
        blockstate.onProjectileHit(this.level, blockstate, hitBlock, this);
        this.setSoundEvent(SoundEvents.ARROW_HIT); // despues se reemplaza
        if (bouncesLeft > 0){
            bounce(hitBlock.getDirection());
            bouncesLeft--;
        } else {
            Spell.ExecuteOnHit( this, hitBlock);
            this.remove();
        }

    }

    public void bounce(Direction direction){
        // si no anda probablemente sea un problema de hitBlock.getDirection()
        System.out.println("bounce");
        Vector3d deltaMovement = getDeltaMovement();
        if (direction == Direction.UP || direction == Direction.DOWN){
            setDeltaMovement(deltaMovement.x, deltaMovement.y * -1, deltaMovement.z);
        } else if (direction == Direction.EAST || direction == Direction.WEST){
            setDeltaMovement(deltaMovement.x * -1, deltaMovement.y, deltaMovement.z);
        } else if (direction == Direction.NORTH || direction == Direction.SOUTH){
            setDeltaMovement(deltaMovement.x, deltaMovement.y, deltaMovement.z * -1);
        }
        // point towards delta movement
        float f = MathHelper.sqrt(getHorizontalDistanceSqr(deltaMovement));
        this.yRot = (float)(MathHelper.atan2(deltaMovement.x, deltaMovement.z) * Simplifier.RadToDeg);
        this.xRot = (float)(MathHelper.atan2(deltaMovement.y, f) * Simplifier.RadToDeg);
        this.yRotO = this.yRot;
        this.xRotO = this.xRot;
    }

    public void setSpellGroup(SpellGroup spellGroup) {
        this.spellGroup = spellGroup;
        this.lifetimeLeft = Spell.getProjectileLifetime() + spellGroup.getSpellProperties().getLifetime();
        this.bouncesLeft = Spell.getProjectileBounces() + spellGroup.getSpellProperties().getBounces();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

}
