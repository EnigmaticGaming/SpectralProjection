package com.eg.SpectralProjection.util.world.worldData;

import com.eg.SpectralProjection.util.nbt.Tags;
import com.eg.SpectralProjection.util.world.multiblock.Multiblock;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class WorldDataMultiblock extends WorldData {

    public static WorldDataMultiblock instance;

    public ArrayList<Multiblock> multiblocks;

    public WorldDataMultiblock(String s) {
        super(s);

        multiblocks = new ArrayList<Multiblock>();
    }

    @Override
    public String getMapName() {
        return "multiblock";
    }

    @Override
    public void setInstance(WorldData worldData) {
        instance = (WorldDataMultiblock) worldData;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        setInstance(this);
        multiblocks.clear();

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
        nbt.setInteger(Tags.Count, multiblocks.size());
        for(int i = 0; i < multiblocks.size(); i++){
            NBTTagCompound multiblockCompound = new NBTTagCompound();

            multiblocks.get(i).writeToNBT(multiblockCompound);

            nbt.setTag(String.valueOf(i), multiblockCompound);
        }
    }

    @Override
    public boolean isDirty() {
        //Very sloppy but will do for now
        return true;
    }
}
