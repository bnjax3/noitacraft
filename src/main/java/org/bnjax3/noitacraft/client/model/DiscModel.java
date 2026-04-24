package org.bnjax3.noitacraft.client.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DiscModel extends EntityModel<Entity> {
    private final ModelRenderer model;

    public DiscModel() {
        texWidth = 16;
        texHeight = 16;

        model = new ModelRenderer(this);
        model.setPos(0, 4, 0.0F);
        model.texOffs(0, 0).addBox(-1.0F, -4.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        model.texOffs(0, 8).addBox(-1.0F, -5.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(10, 0).addBox(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(10, 2).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(4, 8).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(8, 8).addBox(-1.0F, -4.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(0, 10).addBox(-1.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(4, 10).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        model.texOffs(10, 4).addBox(-1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
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