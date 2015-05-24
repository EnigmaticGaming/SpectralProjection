package com.eg.SpectralProjection.gui.client.screen.element;

import com.eg.SpectralProjection.util.helper.HelperGui;
import com.eg.SpectralProjection.util.helper.HelperRender;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Creysys on 24 May 15.
 */
public class ElementIcon extends ElementBase{
    ResourceLocation icon;
    int x;
    int y;
    int width;
    int height;
    int textureWidth;
    int textureHeight;

    String tooltip;

    public ElementIcon(ResourceLocation icon, int x, int y, int width, int height, int textureWidth, int textureHeight){
        this(icon, x, y, width, height, textureWidth, textureHeight, null);
    }

    public ElementIcon(ResourceLocation icon, int x, int y, int width, int height, int textureWidth, int textureHeight, String tooltip){
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;

        this.tooltip = tooltip;
    }

    @Override
    public void drawBackground(int mx, int my) {
        HelperRender.setTexture(icon);
        HelperRender.drawTexturedModalRect(screen.getLeft() + x, screen.getTop() + y, 0, 0, width, height, textureWidth, textureHeight);
    }

    @Override
    public void drawForeground(int mx, int my) {
        if(tooltip != null) {
            HelperGui.drawTooltip(tooltip, x, y, width, height, mx, my, screen);
        }
    }
}
