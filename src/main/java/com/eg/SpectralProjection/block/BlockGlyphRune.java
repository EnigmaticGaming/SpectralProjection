package com.eg.SpectralProjection.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Creysys on 09 Apr 15.
 */
public class BlockGlyphRune extends BlockContainerBase {
    public BlockGlyphRune() {
        super("glyphRune", Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
