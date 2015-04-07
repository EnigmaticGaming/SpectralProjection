package com.eg.SpectralProjection.event.handler;

import com.eg.SpectralProjection.util.world.worldData.WorldData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class HandlerWorld {
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event){
        WorldData.load(event.world);
    }
}
