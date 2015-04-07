package com.eg.SpectralProjection.event.handler;

import com.eg.SpectralProjection.util.world.multiblock.Multiblock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class HandlerTick {
    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if(!event.world.isRemote && event.phase == TickEvent.Phase.END) {
            Multiblock.updateMultiblocks(event.world);

            if(HandlerBlock.ticked) {
                HandlerBlock.clearUpdatedBlocks();
            }
        }
    }
}
