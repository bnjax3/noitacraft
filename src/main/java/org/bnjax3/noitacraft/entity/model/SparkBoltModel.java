package org.bnjax3.noitacraft.entity.model;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.bnjax3.noitacraft.entity.projectiles.SparkBoltProjectile;

public class SparkBoltModel<T extends SparkBoltProjectile> extends EntityModel<T> {
	private final ModelRenderer model;

	public SparkBoltModel() {
        texWidth = 32;
        texHeight = 32;

        model = new ModelRenderer(this);
        model.setPos(0.0F, 24.0F, 0.0F);
        model.texOffs(8, 4).addBox(-8.0F, -3.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        model.texOffs(0, 2).addBox(-7.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        model.texOffs(8, 8).addBox(-7.0F, -3.0F, 1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        model.texOffs(8, 11).addBox(-7.0F, -3.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 14).addBox(-5.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(4, 14).addBox(-5.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(14, 4).addBox(-5.0F, -2.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(14, 6).addBox(-5.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 8).addBox(-5.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        model.texOffs(8, 2).addBox(-3.0F, -2.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 0).addBox(-3.0F, -3.0F, -1.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 12).addBox(-3.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T sparkBolt, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
        model.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		model.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}