package com.eg.SpectralProjection.util;

import com.eg.SpectralProjection.util.nbt.Tags;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class DimensionBlockPos {

    public static DimensionBlockPos readFromNBT(NBTTagCompound compound, String tag) {
        NBTTagCompound posCompound = compound.getCompoundTag(tag);

        int dimension = posCompound.getInteger(Tags.Dimension);
        BlockPos pos = BlockPos.fromLong(posCompound.getLong(Tags.Pos));

        return new DimensionBlockPos(dimension, pos);
    }


    public int dimension;
    public BlockPos pos;

    public DimensionBlockPos(int dimension, BlockPos pos) {
        this.dimension = dimension;
        this.pos = pos;
    }

    public void writeToNBT(NBTTagCompound compound, String tag){
        NBTTagCompound posCompound = new NBTTagCompound();

        posCompound.setInteger(Tags.Dimension, dimension);
        posCompound.setLong(Tags.Pos, pos.toLong());

        compound.setTag(tag, posCompound);
    }
}
