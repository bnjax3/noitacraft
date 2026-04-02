package org.bnjax3.noitacraft.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.client.model.SparkBoltModel;
import org.bnjax3.noitacraft.entity.projectiles.SparkBoltProjectile;

import javax.annotation.ParametersAreNonnullByDefault;

public class SparkBoltRenderer<T extends SparkBoltProjectile> extends EntityRenderer<T>{
    private final SparkBoltModel model;
    private final ResourceLocation TEXTURE = new ResourceLocation(Noitacraft.MOD_ID, "textures/entity/spark_bolt.png");

    public SparkBoltRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager);
        model = new SparkBoltModel();
    }

    @ParametersAreNonnullByDefault
    @Override
    public void render(T projectile, float yaw, float pTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        this.model.setRotationAngle(new ModelRenderer(model), projectile.xRot, projectile.yRot, 0);
        this.model.renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityCutout(TEXTURE)), packedLight, OverlayTexture.NO_OVERLAY,255,255,255,0);
        super.render(projectile, yaw, pTicks, matrixStack, buffer, packedLight);
    }

    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    @Override
    public ResourceLocation getTextureLocation(SparkBoltProjectile projectile) {
        return TEXTURE;
    }
}
