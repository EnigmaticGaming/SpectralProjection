package com.eg.SpectralProjection.util.client;

import com.eg.SpectralProjection.gui.client.screen.ScreenBase;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class GuiUtil {
    public static void drawTooltip(String s, int x, int y, int width, int height, int mx, int my, ScreenBase gui){

        x += gui.getLeft();
        y += gui.getTop();

        if(mx >= x && mx <= x + width && my >= y && my <= y + height){
            ArrayList<String> l = new ArrayList<String>();
            l.add(s);
            gui.drawHoveringText(l, mx - gui.getLeft(), my - gui.getTop(), Minecraft.getMinecraft().fontRendererObj);
        }
    }
}
