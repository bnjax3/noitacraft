package org.bnjax3.noitacraft.client.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BouncyBallModel extends EntityModel<Entity> {
	private final ModelRenderer model;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public BouncyBallModel() {
		texWidth = 16;
		texHeight = 16;

		model = new ModelRenderer(this);
		model.setPos(0, 2, 0);
		model.texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		model.texOffs(0, 4).addBox(0.0F, 1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		model.texOffs(0, 7).addBox(0.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(3.0F, 0.0F, 0.0F);
		model.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -1.5708F, -1.5708F);
		cube_r1.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(1.0F, 0.0F, -2.0F);
		model.addChild(cube_r2);
		setRotationAngle(cube_r2, -1.5708F, 0.0F, 0.0F);
		cube_r2.texOffs(8, 3).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(1.0F, 0.0F, 1.0F);
		model.addChild(cube_r3);
		setRotationAngle(cube_r3, -1.5708F, 0.0F, 0.0F);
		cube_r3.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.0F, 0.0F, 0.0F);
		model.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, -1.5708F, -1.5708F);
		cube_r4.texOffs(8, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		model.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        model.render(matrixStack, buffer, packedLight, packedOverlay);
    }

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}