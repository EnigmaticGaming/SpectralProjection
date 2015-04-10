package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.gui.container.ContainerBase;
import com.eg.SpectralProjection.util.client.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Created by Creysys on 10 Apr 15.
 */
public abstract class ScreenBase extends GuiContainer {

    public ContainerBase container;
    public ResourceLocation texture;

    public int titleX;
    public int titleY;

    public int titleOffsetX;
    public int titleOffsetY;

    public String title;

    public ScreenBase(ContainerBase container) {
        super(container);

        this.container = container;
        this.texture = new ResourceLocation(SpectralProjection.modid, "textures/gui/" + getUnlocalizedName() + ".png");

        this.titleX = 0;
        this.titleY = 0;
        this.titleOffsetX = 0;
        this.titleOffsetY = 0;

        this.title = StatCollector.translateToLocal("gui." + getUnlocalizedName());
    }

    @Override
    public void initGui() {
        super.initGui();

        titleX = guiLeft + (xSize - fontRendererObj.getStringWidth(title)) / 2;
        titleY = guiTop + 5;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderUtil.setTexture(texture);
        RenderUtil.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        fontRendererObj.drawString(title, titleX, titleY, 4210752);
    }

    public World getWorld(){
        return container.world;
    }

    public EntityPlayer getPlayer(){
        return container.player;
    }

    public int getX(){
        return container.x;
    }

    public int getY(){
        return container.y;
    }

    public int getZ(){
        return container.z;
    }


    public abstract String getUnlocalizedName();
}
