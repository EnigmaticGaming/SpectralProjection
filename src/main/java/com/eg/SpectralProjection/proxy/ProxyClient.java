package com.eg.SpectralProjection.proxy;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.util.client.IRenderRegisterHandler;
import com.eg.SpectralProjection.util.client.RenderRegister;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class ProxyClient extends ProxyServer {
    @Override
    public void registerRenderer(Item item) {
        if(item instanceof IRenderRegisterHandler) {
            ((IRenderRegisterHandler)item).registerRenderers();
        } else {
            RenderRegister.register(item, 0, item.getUnlocalizedName().substring(5));
        }
    }

    @Override
    public void registerRenderer(Block block) {
        if(block instanceof IRenderRegisterHandler) {
            ((IRenderRegisterHandler)block).registerRenderers();
        } else {
            RenderRegister.register(block, 0, block.getUnlocalizedName().substring(5));
        }
    }
}
