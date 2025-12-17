package org.bnjax3.noitacraft.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.entity.model.SparkBoltModel;
import org.bnjax3.noitacraft.entity.projectiles.SparkBoltProjectile;

import javax.annotation.ParametersAreNonnullByDefault;

public class SparkBoltRenderer extends EntityRenderer<SparkBoltProjectile>{
    private SparkBoltModel model;

    public SparkBoltRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager);
        model = new SparkBoltModel();
    }

    @Override
    public void render(SparkBoltProjectile projectile, float yaw, float pTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int i) {
        this.model.renderToBuffer(matrixStack, buffer,);
        super.render(projectile, yaw, pTicks, matrixStack, buffer, i);
    }

    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    @Override
    public ResourceLocation getTextureLocation(SparkBoltProjectile projectile) {
        return new ResourceLocation(Noitacraft.MOD_ID, "textures/entity/spark_bolt.png");
    }
}
