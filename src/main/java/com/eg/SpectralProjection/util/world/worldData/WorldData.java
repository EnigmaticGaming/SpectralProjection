package com.eg.SpectralProjection.util.world.worldData;

import com.eg.SpectralProjection.SpectralProjection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.ArrayList;

/**
 * Created by Creysys on 07 Apr 15.
 */
public abstract class WorldData extends WorldSavedData {

    private static ArrayList<Class<? extends WorldData>> worldDataClasses = new ArrayList<Class<? extends WorldData>>();

    public static void registerWorldData(Class<? extends WorldData> c){
        if(!worldDataClasses.contains(c)){
            worldDataClasses.add(c);
        }
    }

    public static void load(World world) {
        if (world.isRemote || world instanceof WorldServerMulti) {
            return;
        }

        for (int i = 0; i < worldDataClasses.size(); i++) {
            WorldData worldData;

            try {
                worldData = worldDataClasses.get(i).getConstructor(String.class).newInstance((String) null);
            } catch (Throwable t) {
                FMLCommonHandler.instance().raiseException(t, "Exception thrown in constructor of " + worldDataClasses.get(i).getCanonicalName(), false);
                continue;
            }


            WorldData existing = (WorldData) world.getPerWorldStorage().loadData(worldDataClasses.get(i), worldData.mapName);

            if (existing == null) {
                worldData.setInstance(worldData);
                world.getPerWorldStorage().setData(worldData.mapName, worldData);
            }
        }
    }


    @SuppressWarnings("unused")
    public WorldData(String name) {
        super(null);
        ReflectionHelper.setPrivateValue(WorldSavedData.class, this, SpectralProjection.modid + "-" + getMapName(), "mapName");
    }

    public abstract String getMapName();
    public abstract void setInstance(WorldData worldData);
}
