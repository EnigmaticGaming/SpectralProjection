package com.eg.SpectralProjection.proxy;

import com.eg.SpectralProjection.SpectralProjection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class ProxyClient extends ProxyServer {
    @Override
    public void registerItemRenderer(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SpectralProjection.modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
