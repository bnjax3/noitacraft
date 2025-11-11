package org.bnjax3.noitacraft.gui.screen.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.bnjax3.noitacraft.Noitacraft;
import org.bnjax3.noitacraft.container.containers.WandAltarContainer;

public class WandAltarScreen extends ContainerScreen<WandAltarContainer> {
    private final ResourceLocation GUI = new ResourceLocation(Noitacraft.MOD_ID, "textures/gui/wand_altar_gui.png");

    public WandAltarScreen(WandAltarContainer wandAltarContainer, PlayerInventory inventory, ITextComponent textComponent) {
        super(wandAltarContainer, inventory, textComponent);
        this.imageHeight = 200;
        this.inventoryLabelY = 108;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float f, int x, int y) {
        RenderSystem.color4f(1,1,1,1);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(GUI);
        int left = this.getGuiLeft();
        int top = this.getGuiTop();
        this.blit(matrixStack, left, top, 0, 0, this.getXSize(), this.getYSize());
    }

}
