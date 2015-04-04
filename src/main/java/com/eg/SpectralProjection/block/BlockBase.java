package com.eg.SpectralProjection.block;


import com.eg.SpectralProjection.SpectralProjection;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class BlockBase extends Block {

    public BlockBase(String name, Material material) {
        super(material);
        setCreativeTab(SpectralProjection.creativeTab);
        setUnlocalizedName(name);
        GameRegistry.registerBlock(this, name);
    }
}
