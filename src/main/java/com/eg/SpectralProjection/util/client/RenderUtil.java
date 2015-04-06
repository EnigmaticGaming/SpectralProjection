package com.eg.SpectralProjection.util.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class RenderUtil {
    public static void drawTexturedModalRect(int x, int y, float u, float v, int width, int height){
        drawTexturedModalRect(x, y, u, v, width, height, 256, 256);
    }

    public static void drawTexturedModalRect(int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 1F / (float) textureWidth;
        float f1 = 1F / (float) textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer renderer = tessellator.getWorldRenderer();
        renderer.startDrawingQuads();
        renderer.addVertexWithUV(x, y + height, 0, u * f, (v + height) * f1);
        renderer.addVertexWithUV(x + width, y + height, 0, (u + width) * f, (v + height) * f1);
        renderer.addVertexWithUV(x + width, y, 0, (u + width) * f, v * f1);
        renderer.addVertexWithUV(x, y, 0, u * f, v * f1);
        tessellator.draw();
    }

    public static void setTexture(ResourceLocation texture) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
    }
}
