package org.bnjax3.noitacraft.spell;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class ProjectileEntity extends Entity{

    private UUID ownerUUID;
    private int ownerNetworkId;
    private boolean leftOwner;

    ProjectileEntity(EntityType<? extends net.minecraft.entity.projectile.ProjectileEntity> p_i231584_1_, World p_i231584_2_) {
        super(p_i231584_1_, p_i231584_2_);
    }

    public void setOwner(@Nullable Entity p_212361_1_) {
        if (p_212361_1_ != null) {
            this.ownerUUID = p_212361_1_.getUUID();
            this.ownerNetworkId = p_212361_1_.getId();
        }

    }

    @Nullable
    public Entity getOwner() {
        if (this.ownerUUID != null && this.level instanceof ServerWorld) {
            return ((ServerWorld)this.level).getEntity(this.ownerUUID);
        } else {
            return this.ownerNetworkId != 0 ? this.level.getEntity(this.ownerNetworkId) : null;
        }
    }

    protected void addAdditionalSaveData(CompoundNBT nbt) {
        if (this.ownerUUID != null) {
            nbt.putUUID("Owner", this.ownerUUID);
        }

        if (this.leftOwner) {
            nbt.putBoolean("LeftOwner", true);
        }

    }

    protected void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.hasUUID("Owner")) {
            this.ownerUUID = nbt.getUUID("Owner");
        }

        this.leftOwner = nbt.getBoolean("LeftOwner");
    }

    public void tick() {
        if (!this.leftOwner) {
            this.leftOwner = this.checkLeftOwner();
        }

        super.tick();
    }

    private boolean checkLeftOwner() {
        Entity owner = this.getOwner();
        if (owner != null) {
            for(Entity entity1 : this.level.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (entity) -> {
                return !entity.isSpectator() && entity.isPickable();
            })) {
                if (entity1.getRootVehicle() == owner.getRootVehicle()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void shoot(double x, double y, double z, float magnitude, float spread) {
        Vector3d vector3d = (new Vector3d(x, y, z)).normalize().add(this.random.nextGaussian() * (double)0.0075F * (double) spread, this.random.nextGaussian() * (double)0.0075F * (double) spread, this.random.nextGaussian() * (double)0.0075F * (double) spread).scale((double) magnitude);
        this.setDeltaMovement(vector3d);
        float f = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
        this.yRot = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
        this.xRot = (float)(MathHelper.atan2(vector3d.y, (double)f) * (double)(180F / (float)Math.PI));
        this.yRotO = this.yRot;
        this.xRotO = this.xRot;
    }

    public void shootFromRotation(Entity entity, float x, float y, float z, float magnitude, float spread) {
        float xd = -MathHelper.sin(y * ((float)Math.PI / 180F)) * MathHelper.cos(x * ((float)Math.PI / 180F));
        float yd = -MathHelper.sin((x + z) * ((float)Math.PI / 180F));
        float zd = MathHelper.cos(y * ((float)Math.PI / 180F)) * MathHelper.cos(x * ((float)Math.PI / 180F));
        this.shoot((double) xd, (double) yd, (double) zd, magnitude, spread);
        Vector3d vector3d = entity.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vector3d.x, entity.isOnGround() ? 0.0D : vector3d.y, vector3d.z));
    }

    protected void onHit(RayTraceResult traceResult) {
        RayTraceResult.Type raytraceresult$type = traceResult.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onHitEntity((EntityRayTraceResult)traceResult);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            this.onHitBlock((BlockRayTraceResult)traceResult);
        }

    }

    protected void onHitEntity(EntityRayTraceResult hitEntity) {
    }

    protected void onHitBlock(BlockRayTraceResult hitBlock) {
        BlockState blockstate = this.level.getBlockState(hitBlock.getBlockPos());
        // blockstate.onProjectileHit(this.level, blockstate, hitBlock, this);
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double x, double y, double z) {
        this.setDeltaMovement(x, y, z);
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = MathHelper.sqrt(x * x + z * z);
            this.xRot = (float)(MathHelper.atan2(y, (double)f) * (double)(180F / (float)Math.PI));
            this.yRot = (float)(MathHelper.atan2(x, z) * (double)(180F / (float)Math.PI));
            this.xRotO = this.xRot;
            this.yRotO = this.yRot;
            this.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
        }

    }

    protected boolean canHitEntity(Entity entity) {
        if (!entity.isSpectator() && entity.isAlive() && entity.isPickable()) {
            Entity owner = this.getOwner();
            return owner == null || this.leftOwner || !owner.isPassengerOfSameVehicle(entity);
        } else {
            return false;
        }
    }

    protected void updateRotation() {
        Vector3d vector3d = this.getDeltaMovement();
        float f = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
        this.xRot = lerpRotation(this.xRotO, (float)(MathHelper.atan2(vector3d.y, (double)f) * (double)(180F / (float)Math.PI)));
        this.yRot = lerpRotation(this.yRotO, (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI)));
    }

    protected static float lerpRotation(float p_234614_0_, float p_234614_1_) {
        while(p_234614_1_ - p_234614_0_ < -180.0F) {
            p_234614_0_ -= 360.0F;
        }

        while(p_234614_1_ - p_234614_0_ >= 180.0F) {
            p_234614_0_ += 360.0F;
        }

        return MathHelper.lerp(0.2F, p_234614_0_, p_234614_1_);
    }
}
