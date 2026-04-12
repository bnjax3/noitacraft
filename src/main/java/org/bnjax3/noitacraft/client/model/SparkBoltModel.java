package org.bnjax3.noitacraft.client.model;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.bnjax3.noitacraft.entity.projectiles.SparkBoltProjectile;

public class SparkBoltModel<T extends SparkBoltProjectile> extends EntityModel<T> {

    private final ModelRenderer model;

    public SparkBoltModel() {
        texWidth = 32;
        texHeight = 32;

        model = new ModelRenderer(this);
        model.setPos(0, 0, 0);
        setRotationAngle(0, 0, 0.0F);
        model.texOffs(8, 4).addBox(-4.75F, 0.9167F, -0.9167F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        model.texOffs(0, 2).addBox(-3.75F, -0.0833F, -0.9167F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        model.texOffs(8, 8).addBox(-3.75F, 0.9167F, 1.0833F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        model.texOffs(8, 11).addBox(-3.75F, 0.9167F, -1.9167F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 14).addBox(-1.75F, 0.9167F, 1.0833F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(4, 14).addBox(-1.75F, -0.0833F, 0.0833F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(14, 4).addBox(-1.75F, 1.9167F, -1.9167F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(14, 6).addBox(-1.75F, 2.9167F, -0.9167F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 8).addBox(-1.75F, 0.9167F, -0.9167F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        model.texOffs(8, 2).addBox(0.25F, 1.9167F, 0.0833F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 0).addBox(0.25F, 0.9167F, -0.9167F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 12).addBox(0.25F, 1.9167F, -0.9167F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    }

    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        model.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        model.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}

