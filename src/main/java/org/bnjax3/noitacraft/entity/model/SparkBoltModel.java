package org.bnjax3.noitacraft.entity.model;// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.bnjax3.noitacraft.entity.projectiles.SparkBoltProjectile;
import org.bnjax3.noitacraft.spell.projectile.MagicProjectile;

import javax.annotation.ParametersAreNonnullByDefault;

public class SparkBoltModel extends EntityModel<SparkBoltProjectile> {
	private final ModelRenderer bb_main;
	private final ModelRenderer head1_r1;

    @ParametersAreNonnullByDefault
    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.renderToBuffer(p_225598_1_,p_225598_2_,p_225598_3_,p_225598_4_);
    }

    public SparkBoltModel() {
		texWidth = 32;
		texHeight = 32;

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(14, 20).addBox(0.0F, -3.0F, -7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(20, 20).addBox(0.0F, -6.0F, -7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(14, 11).addBox(-2.0F, -5.0F, -7.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(6, 22).addBox(1.0F, -4.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main.texOffs(14, 15).addBox(0.0F, -4.0F, -5.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bb_main.texOffs(0, 18).addBox(-1.0F, -6.0F, -7.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		bb_main.texOffs(10, 22).addBox(0.0F, -6.0F, -5.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main.texOffs(22, 6).addBox(0.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main.texOffs(0, 22).addBox(-1.0F, -3.0F, -7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(22, 3).addBox(0.0F, -3.0F, -7.0F, 0.0F, 1.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(22, 8).addBox(-2.0F, -5.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bb_main.texOffs(0, 11).addBox(-1.0F, -4.0F, -5.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		bb_main.texOffs(0, 0).addBox(0.0F, -5.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		bb_main.texOffs(22, 0).addBox(-1.0F, -5.0F, -5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		head1_r1 = new ModelRenderer(this);
		head1_r1.setPos(0.0F, -3.0F, -7.0F);
		bb_main.addChild(head1_r1);
		setRotationAngle(head1_r1, 0.0F, -1.5708F, 0.0F);
		head1_r1.texOffs(8, 18).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
	}

    @Override
    public void setupAnim(SparkBoltProjectile p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}