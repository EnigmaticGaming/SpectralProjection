package com.eg.SpectralProjection.util.client;

import com.eg.SpectralProjection.SpectralProjection;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class RenderRegister {
    public static void register(Block block, int meta, String file) {
        register(Item.getItemFromBlock(block), meta, file);
    }

    public static void register(Item item, int meta, String file) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(SpectralProjection.modid + ":" + file, "inventory"));
    }
}
