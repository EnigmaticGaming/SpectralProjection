package com.eg.SpectralProjection.util.world.multiblock;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.event.handler.HandlerBlock;
import com.eg.SpectralProjection.util.DimensionBlockPos;
import com.eg.SpectralProjection.util.nbt.Tags;
import com.eg.SpectralProjection.util.world.worldData.WorldDataMultiblock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Creysys on 07 Apr 15.
 */
public abstract class Multiblock {

    public static Hashtable<String, Class<? extends Multiblock>> registeredMultiblocks = new Hashtable<String, Class<? extends Multiblock>>();

    private static Multiblock newMultiblock(Class<? extends Multiblock> c) {
        Multiblock multiblock = null;
        try {
            multiblock = c.newInstance();
        } catch (Throwable t) {
            FMLCommonHandler.instance().raiseException(t, "Exception thrown in constructor of " + c.getCanonicalName(), false);
        }
        return multiblock;
    }


    public static void registerMultiblock(String name, Class<? extends Multiblock> c) {
        if (!registeredMultiblocks.contains(c)) {
            registeredMultiblocks.put(name, c);
        }
    }

    public static void tryCreateMultiblock(World world, BlockPos pos) {
        for (Map.Entry<String, Class<? extends Multiblock>> entry : registeredMultiblocks.entrySet()) {
            Multiblock multiblock = newMultiblock(entry.getValue());

            if (multiblock != null) {
                multiblock.anchor = multiblock.findAnchor(world, pos);

                if(getMultiblock(multiblock.anchor.dimension, multiblock.anchor.pos) == null && multiblock.checkStructure()) {
                    multiblock.active = true;
                    addMultiblock(multiblock);
                }
            }
        }
    }

    public static void addMultiblock(Multiblock multiblock) {
        if (!getMultiblocks().contains(multiblock)) {
            getMultiblocks().add(multiblock);
        }
    }

    public static void removeMultiblock(Multiblock multiblock) {
        if (getMultiblocks().contains(multiblock)) {
            getMultiblocks().remove(multiblock);
        }
    }

    public static ArrayList<Multiblock> getMultiblocks() {
        return WorldDataMultiblock.instance.multiblocks;
    }

    public static void updateMultiblocks(World world) {

        if(world.isRemote){
            return;
        }


        for (int i = 0; i < getMultiblocks().size(); i++) {
            Multiblock multiblock = getMultiblocks().get(i);
            if (multiblock.isActive() && multiblock.getAnchor().dimension == world.provider.getDimensionId() && world.isBlockLoaded(multiblock.getAnchor().pos)) {
                multiblock.update(world);
            }
        }

        ArrayList<DimensionBlockPos> updatedBlocks = HandlerBlock.getUpdatedBlocks();
        for (int i = 0; i < updatedBlocks.size(); i++) {
            DimensionBlockPos blockPos = updatedBlocks.get(i);
            World blockWorld = DimensionManager.getWorld(blockPos.dimension);

            boolean contained = false;
            if (blockWorld != null && blockWorld.isBlockLoaded(blockPos.pos)) {
                for (int j = 0; j < getMultiblocks().size(); j++) {
                    Multiblock multiblock = getMultiblocks().get(i);
                    if (multiblock.getAnchor().dimension == blockWorld.provider.getDimensionId() && multiblock.contains(blockPos.pos)) {
                        contained = true;
                        multiblock.active = multiblock.checkStructure();
                        if(!multiblock.active && multiblock.shouldDestroy()){
                            removeMultiblock(multiblock);
                        }
                        break;
                    }
                }
            }

            if (!contained) {
                tryCreateMultiblock(blockWorld, blockPos.pos);
            }
        }
    }

    public static Multiblock getMultiblock(int dimension, BlockPos pos){
        for(int i = 0; i < getMultiblocks().size(); i++){
            Multiblock multiblock = getMultiblocks().get(i);
            if(multiblock.getAnchor().dimension == dimension && multiblock.contains(pos)){
                return multiblock;
            }
        }

        return null;
    }

    public static Multiblock fromNBT(NBTTagCompound nbt) {
        String name = nbt.getString(Tags.Name);

        if (registeredMultiblocks.containsKey(name)) {
            Multiblock multiblock = newMultiblock(registeredMultiblocks.get(name));

            if (multiblock != null) {
                multiblock.readFromNBT(nbt);
                return multiblock;
            }
        }

        return null;
    }

    public static String getMultiblockName(Multiblock multiblock){
        Class multiblockClass = multiblock.getClass();

        for(Map.Entry<String, Class<? extends Multiblock>> entry : registeredMultiblocks.entrySet()){
            if(entry.getValue() == multiblockClass){
                return entry.getKey();
            }
        }

        return null;
    }


    private DimensionBlockPos anchor;

    private boolean active;

    public Multiblock() {
        active = false;
    }

    public DimensionBlockPos getAnchor() {
        return anchor;
    }

    public boolean isActive() {
        return active;
    }

    public World getWorld() {
        return DimensionManager.getWorld(anchor.dimension);
    }

    public abstract DimensionBlockPos findAnchor(World world, BlockPos pos);

    public abstract boolean checkStructure();

    public abstract boolean contains(BlockPos pos);

    public abstract boolean shouldDestroy();

    public abstract void update(World world);

    public void readFromNBT(NBTTagCompound compound) {
        anchor = DimensionBlockPos.readFromNBT(compound, Tags.Pos);
        active = compound.getBoolean(Tags.Active);
    }

    public void writeToNBT(NBTTagCompound compound) {
        String name = getMultiblockName(this);

        if(name == null){
            FMLCommonHandler.instance().raiseException(new NullPointerException(), "Multiblock not registered!", true);
        }

        compound.setString(Tags.Name, name);


        anchor.writeToNBT(compound, Tags.Pos);
        compound.setBoolean(Tags.Active, isActive());
    }
}
