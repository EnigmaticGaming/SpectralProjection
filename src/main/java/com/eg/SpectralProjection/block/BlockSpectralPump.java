package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.tileEntity.TileEntitySpectralPump;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Creysys on 09 Apr 15.
 */
public class BlockSpectralPump extends BlockContainerBase {
    public BlockSpectralPump() {
        super("spectralPump", Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySpectralPump();
    }
}
