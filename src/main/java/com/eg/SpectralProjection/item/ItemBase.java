package com.eg.SpectralProjection.item;

import com.eg.SpectralProjection.SpectralProjection;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class ItemBase extends Item {
    public ItemBase(String name){
        setCreativeTab(SpectralProjection.creativeTab);
        setUnlocalizedName(name);
        GameRegistry.registerItem(this, name);
    }
}
