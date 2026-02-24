package org.bnjax3.noitacraft.spell.projectile.nttttt;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public abstract class AbstractFunctionalProjectile extends ProjectileBase {
    private float gravity = 0.05f;
    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    private static final DataParameter<Byte> ID_FLAGS = EntityDataManager.defineId(AbstractFunctionalProjectile.class, DataSerializers.BYTE);
    private double baseDamage = 2;
    private int knockback = 0;

    protected AbstractFunctionalProjectile(EntityType<? extends AbstractFunctionalProjectile> p_i48546_1_, World p_i48546_2_) {
        super(p_i48546_1_, p_i48546_2_);
    }

    protected AbstractFunctionalProjectile(EntityType<? extends AbstractFunctionalProjectile> p_i48547_1_, double p_i48547_2_, double p_i48547_4_, double p_i48547_6_, World p_i48547_8_) {
        this(p_i48547_1_, p_i48547_8_);
        this.setPos(p_i48547_2_, p_i48547_4_, p_i48547_6_);
    }

    protected AbstractFunctionalProjectile(EntityType<? extends AbstractFunctionalProjectile> type, LivingEntity entity, World world) {
        this(type, entity.getX(), entity.getEyeY() - (double)0.1F, entity.getZ(), world);
        this.setOwner(entity);
    }

    public void setSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderAtSqrDistance(double dist) {
        double d0 = this.getBoundingBox().getSize() * 10.0D;
        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }

        d0 = d0 * 64.0D * getViewScale();
        return dist < d0 * d0;
    }

    protected void defineSynchedData() {
        this.entityData.define(ID_FLAGS, (byte)0);
    }

    public void shoot(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
        super.shoot(p_70186_1_, p_70186_3_, p_70186_5_, p_70186_7_, p_70186_8_);
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
        this.setPos(p_180426_1_, p_180426_3_, p_180426_5_);
        this.setRot(p_180426_7_, p_180426_8_);
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
        super.lerpMotion(p_70016_1_, p_70016_3_, p_70016_5_);
    }

    public void tick() {
        super.tick();
        Vector3d vector3d = this.getDeltaMovement();

        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
            this.yRot = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
            this.xRot = (float)(MathHelper.atan2(vector3d.y, (double)f) * (double)(180F / (float)Math.PI));
            this.yRotO = this.yRot;
            this.xRotO = this.xRot;
        }

        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level.getBlockState(blockpos);

        if (this.isInWaterOrRain()) {
            this.clearFire();
        }
            Vector3d vector3d2 = this.position();
            Vector3d vector3d3 = vector3d2.add(vector3d);
            RayTraceResult raytraceresult = this.level.clip(new RayTraceContext(vector3d2, vector3d3, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
            if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
                vector3d3 = raytraceresult.getLocation();
            }

            while(!this.removed) {
                EntityRayTraceResult entityraytraceresult = this.findHitEntity(vector3d2, vector3d3);
                if (entityraytraceresult != null) {
                    raytraceresult = entityraytraceresult;
                }

                if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                    Entity entity = ((EntityRayTraceResult)raytraceresult).getEntity();
                    Entity entity1 = this.getOwner();
                    if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity)entity1).canHarmPlayer((PlayerEntity)entity)) {
                        raytraceresult = null;
                        entityraytraceresult = null;
                    }
                }

                if (raytraceresult != null && raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                    this.onHit(raytraceresult);
                    this.hasImpulse = true;
                }

                if (entityraytraceresult == null) {
                    break;
                }

                raytraceresult = null;
            }

            vector3d = this.getDeltaMovement();
            double DMX = vector3d.x;
            double DMY = vector3d.y;
            double DMZ = vector3d.z;
            double x = this.getX() + DMX;
            double y = this.getY() + DMY;
            double z = this.getZ() + DMZ;
            float f1 = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
            this.yRot = (float)(MathHelper.atan2(DMX, DMZ) * (double)(180F / (float)Math.PI));


            this.xRot = (float)(MathHelper.atan2(DMY, f1) * (double)(180F / (float)Math.PI));
            this.xRot = lerpRotation(this.xRotO, this.xRot);
            this.yRot = lerpRotation(this.yRotO, this.yRot);
            float f2 = 0.99F;
            float f3 = 0.05F;
            if (this.isInWater()) {
                for(int j = 0; j < 4; ++j) {
                    float f4 = 0.25F;
                    this.level.addParticle(ParticleTypes.BUBBLE, x - DMX * 0.25D, y - DMY * 0.25D, z - DMZ * 0.25D, DMX, DMY, DMZ);
                }

                f2 = this.getWaterInertia();
            }

            this.setDeltaMovement(vector3d.scale((double)f2));
            Vector3d vector3d4 = this.getDeltaMovement();
            this.setDeltaMovement(vector3d4.x, vector3d4.y - (double)0.05F, vector3d4.z);


            this.setPos(x, y, z);
            this.checkInsideBlocks();

    }

    public void move(MoverType moverType, Vector3d vector3d) {
        super.move(moverType, vector3d);

    }


    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        Entity owner = this.getOwner();
        DamageSource damagesource;
        if (owner == null) {
            damagesource = DamageSource.indirectMobAttack(this, null);
        } else {
            damagesource = DamageSource.indirectMobAttack(this, (LivingEntity) owner);
            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(entity);
            }
        }

        boolean enderman = entity.getType() == EntityType.ENDERMAN;
        int k = entity.getRemainingFireTicks();
        if (this.isOnFire() && !enderman) {
            entity.setSecondsOnFire(5);
        }

        if (canHurt(entity, damagesource, baseDamage)) {
            if (enderman) {
                return;
            }
            this.hurtEntrity(entity, damagesource, baseDamage);
            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                /* for cosmetic arrows in body
                if (!this.level.isClientSide) {
                    livingentity.setArrowCount(livingentity.getArrowCount() + 1);
                }
                 */

                if (this.knockback > 0) {
                    Vector3d vector3d = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
                    if (vector3d.lengthSqr() > 0.0D) {
                        livingentity.push(vector3d.x, 0.1D, vector3d.z);
                    }
                }

                this.doPostHurtEffects(livingentity);
                if (livingentity != owner && livingentity instanceof PlayerEntity && owner instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity)owner).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));
                }

            }

            this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.remove();



        } else {
            entity.setRemainingFireTicks(k);
            // bounce off
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.yRot += 180.0F;
            this.yRotO += 180.0F;
        }

    }

    private void hurtEntrity(Entity entity, DamageSource damagesource, double baseDamage) {
        entity.hurt(damagesource, (float) baseDamage);
    }

    private boolean canHurt(Entity entity, DamageSource damagesource, double baseDamage) {
        if (isInvulnerableTo(damagesource)){
            // despues mepa
        }
        return true;
    }

    protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
        super.onHitBlock(p_230299_1_);
        this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.setSoundEvent(SoundEvents.ARROW_HIT);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ARROW_HIT;
    }

    protected final SoundEvent getHitGroundSoundEvent() {
        return this.soundEvent;
    }

    protected void doPostHurtEffects(LivingEntity p_184548_1_) {
    }

    @Nullable
    protected EntityRayTraceResult findHitEntity(Vector3d p_213866_1_, Vector3d p_213866_2_) {
        return ProjectileHelper.getEntityHitResult(this.level, this, p_213866_1_, p_213866_2_, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), this::canHitEntity);
    }

    protected boolean canHitEntity(Entity p_230298_1_) {
        return super.canHitEntity(p_230298_1_);
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putDouble("damage", this.baseDamage);
        p_213281_1_.putString("SoundEvent", Registry.SOUND_EVENT.getKey(this.soundEvent).toString());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        if (p_70037_1_.contains("damage", 99)) {
            this.baseDamage = p_70037_1_.getDouble("damage");
        }
        if (p_70037_1_.contains("SoundEvent", 8)) {
            this.soundEvent = Registry.SOUND_EVENT.getOptional(new ResourceLocation(p_70037_1_.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
        }
    }

    protected boolean isMovementNoisy() {
        return false;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public double getBaseDamage() {
        return this.baseDamage;
    }

    public void setKnockback(int knockback) {
        this.knockback = knockback;
    }

    public boolean isAttackable() {
        return false;
    }

    protected float getEyeHeight(Pose p_213316_1_, EntitySize p_213316_2_) {
        return 0.13F;
    }


    private void setFlag(int p_203049_1_, boolean p_203049_2_) {
        byte b0 = this.entityData.get(ID_FLAGS);
        if (p_203049_2_) {
            this.entityData.set(ID_FLAGS, (byte)(b0 | p_203049_1_));
        } else {
            this.entityData.set(ID_FLAGS, (byte)(b0 & ~p_203049_1_));
        }

    }

    protected float getWaterInertia() {
        return 0.6F;
    }

    public void setNoPhysics(boolean noPhysics) {
        this.noPhysics = noPhysics;
        this.setFlag(2, noPhysics);
    }

    public boolean isNoPhysics() {
        if (!this.level.isClientSide) {
            return this.noPhysics;
        } else {
            return (this.entityData.get(ID_FLAGS) & 2) != 0;
        }
    }

    public IPacket<?> getAddEntityPacket() {
        Entity entity = this.getOwner();
        return new SSpawnObjectPacket(this, entity == null ? 0 : entity.getId());
    }

    public float getGravity() {
        return gravity;
    }

    public AbstractFunctionalProjectile setGravity(float gravity) {
        this.gravity = gravity;
        return this;
    }

}
