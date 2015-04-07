package com.eg.SpectralProjection.util.world.worldData;

import com.eg.SpectralProjection.util.nbt.Tags;
import com.eg.SpectralProjection.util.world.multiblock.Multiblock;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class WorldDataMultiblock extends WorldData {

    public static WorldDataMultiblock instance;

    public WorldDataMultiblock(){
        this("multiblock");
    }
    public WorldDataMultiblock(String name) {
        super(name);
    }

    @Override
    public void setInstance(WorldData worldData) {
        instance = (WorldDataMultiblock) worldData;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        int count = nbt.getInteger(Tags.Count);
        for(int i = 0; i < count; i++){
            NBTTagCompound multiblockCompound = nbt.getCompoundTag(String.valueOf(i));
            Multiblock multiblock = Multiblock.fromNBT(multiblockCompound);

            if(multiblock != null) {
                Multiblock.addMultiblock(multiblock);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {

    }
}
