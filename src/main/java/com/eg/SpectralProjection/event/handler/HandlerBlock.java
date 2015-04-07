package com.eg.SpectralProjection.event.handler;

import com.eg.SpectralProjection.util.DimensionBlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class HandlerBlock {

    private static ArrayList<DimensionBlockPos> updatedBlocks = new ArrayList<DimensionBlockPos>();
    public static boolean ticked = false;

    public static ArrayList<DimensionBlockPos> getUpdatedBlocks(){
        ticked = true;
        return updatedBlocks;
    }

    public static void clearUpdatedBlocks(){
        ticked = false;
        updatedBlocks.clear();
    }


    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if(!event.world.isRemote) {
            updatedBlocks.add(new DimensionBlockPos(event.world.provider.getDimensionId(), event.pos));
        }
    }

    @SubscribeEvent
    public void onBlockPlace(BlockEvent.PlaceEvent event){
        if(!event.world.isRemote) {
            updatedBlocks.add(new DimensionBlockPos(event.world.provider.getDimensionId(), event.pos));
        }
    }
}
