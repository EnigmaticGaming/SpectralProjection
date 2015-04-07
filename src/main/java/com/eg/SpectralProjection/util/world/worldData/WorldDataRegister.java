package com.eg.SpectralProjection.util.world.worldData;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class WorldDataRegister {
    public static void register(){
        //Register world data objects here
        WorldData.registerWorldData(WorldDataMultiblock.class);
    }
}
