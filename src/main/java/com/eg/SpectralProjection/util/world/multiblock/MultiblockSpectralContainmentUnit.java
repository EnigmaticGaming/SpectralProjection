package com.eg.SpectralProjection.util.world.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class MultiblockSpectralContainmentUnit extends Multiblock {
    public MultiblockSpectralContainmentUnit(BlockPos anchor) {
        super(anchor);
    }

    @Override
    public boolean contains(BlockPos pos) {
        return false;
    }

    @Override
    public boolean checkStructure() {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {

    }
}
