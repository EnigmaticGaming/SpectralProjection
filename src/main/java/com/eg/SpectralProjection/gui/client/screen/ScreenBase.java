package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.gui.client.screen.element.ElementBase;
import com.eg.SpectralProjection.gui.container.ContainerBase;
import com.eg.SpectralProjection.util.helper.HelperRender;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

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

    private ArrayList<ElementBase> elements;

    public ScreenBase(ContainerBase container, ResourceLocation texture) {
        super(container);

        this.container = container;
        this.texture = texture;

        this.titleX = 0;
        this.titleY = 0;
        this.titleOffsetX = 0;
        this.titleOffsetY = 0;

        this.title = StatCollector.translateToLocal("gui." + getUnlocalizedName());

        elements = new ArrayList<ElementBase>();
    }

    public void add(ElementBase element){
        element.add(this);
        elements.add(element);
    }

    @Override
    public void initGui() {
        super.initGui();

        titleX = guiLeft + (xSize - fontRendererObj.getStringWidth(title)) / 2;
        titleY = guiTop + 5;

        for (ElementBase element : elements) {
            element.init();
        }
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mx, int my) {
        HelperRender.setTexture(texture);
        HelperRender.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        fontRendererObj.drawString(title, titleX, titleY, 4210752);

        for (ElementBase element : elements) {
            element.drawBackground(mx, my);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        for (ElementBase element : elements) {
            element.drawForeground(mx, my);
        }
    }

    @Override
    public void drawHoveringText(List textLines, int x, int y, FontRenderer font) {
        super.drawHoveringText(textLines, x, y, font);
    }

    public World getWorld() {
        return container.world;
    }

    public EntityPlayer getPlayer() {
        return container.player;
    }

    public int getLeft() {
        return guiLeft;
    }

    public int getTop() {
        return guiTop;
    }


    public int getX() {
        return container.x;
    }

    public int getY() {
        return container.y;
    }

    public int getZ() {
        return container.z;
    }


    public abstract String getUnlocalizedName();
}
