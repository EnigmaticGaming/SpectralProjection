package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.gui.GuiID;
import com.eg.SpectralProjection.util.world.multiblock.Multiblock;
import com.eg.SpectralProjection.util.world.multiblock.MultiblockSpectralContainmentUnit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Creysys on 08 Apr 15.
 */
public class BlockSpectralVent extends BlockBase {
    public BlockSpectralVent() {
        super("spectralVent", Material.iron);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(world.isRemote){
            return true;
        }

        Multiblock multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), pos);

        if(multiblock instanceof MultiblockSpectralContainmentUnit && multiblock.isActive()){
            player.openGui(SpectralProjection.instance, GuiID.SpectralContainmentUnit.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }

        return false;
    }
}
