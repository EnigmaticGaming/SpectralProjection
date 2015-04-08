package com.eg.SpectralProjection.event;

import com.eg.SpectralProjection.event.handler.HandlerBlock;
import com.eg.SpectralProjection.event.handler.HandlerTick;
import com.eg.SpectralProjection.event.handler.HandlerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class EventRegister {
    public static void register(){
        MinecraftForge.EVENT_BUS.register(new HandlerWorld());
        MinecraftForge.EVENT_BUS.register(new HandlerBlock());

        FMLCommonHandler.instance().bus().register(new HandlerTick());
    }
}
