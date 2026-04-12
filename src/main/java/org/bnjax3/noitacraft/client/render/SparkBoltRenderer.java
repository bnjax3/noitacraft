package org.bnjax3.noitacraft.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
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
        matrixStack.pushPose();

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(pTicks, projectile.yRotO, projectile.yRot) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(pTicks, projectile.xRotO, projectile.xRot)));
        // matrixStack.mulPose(Vector3f.XP.rotationDegrees(45.0F));

        // matrixStack.scale(0.05625F, 0.05625F, 0.05625F);
        // matrixStack.translate(-2.0D, 0.0D, 0.0D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));

        IVertexBuilder vb = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
        this.model.renderToBuffer(matrixStack, vb, packedLight, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
        super.render(projectile, yaw, pTicks, matrixStack, buffer, packedLight);
    }

    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    @Override
    public ResourceLocation getTextureLocation(SparkBoltProjectile projectile) {
        return TEXTURE;
    }
}
