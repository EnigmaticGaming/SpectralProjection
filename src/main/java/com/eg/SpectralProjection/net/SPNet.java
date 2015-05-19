package com.eg.SpectralProjection.net;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.net.packet.PacketBase;
import com.eg.SpectralProjection.net.packet.PacketTESync;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

/**
 * Created by Creysys on 19 May 15.
 */
public class SPNet {
    private static SimpleNetworkWrapper network;

    public static void initialize(){
        network = NetworkRegistry.INSTANCE.newSimpleChannel(SpectralProjection.MODID);

        ArrayList<Class< ? extends PacketBase>> l = new ArrayList<Class<? extends PacketBase>>();
        registerPackets(l);
        int i = 0;
        for (Class<? extends PacketBase> entry : l) {
            PacketBase handler = null;
            try {
                handler = entry.newInstance();
            } catch (Exception e) {
                FMLCommonHandler.instance().raiseException(e, "Error registering network packet", true);
            }

            if(handler != null) {
                if (handler.side == SPSide.ALL) {
                    network.registerMessage(handler, entry, i, Side.CLIENT);
                    network.registerMessage(handler, entry, i, Side.SERVER);
                } else {
                    network.registerMessage(handler, entry, i, handler.side.toSide());
                }
            }

            i++;
        }
    }

    private static void registerPackets(ArrayList<Class< ? extends PacketBase>> l){
        l.add(PacketTESync.class);
    }


    public static void sendToServer(PacketBase packet){
        network.sendToServer(packet);
    }

    public static void sendTo(PacketBase packet, EntityPlayerMP target){
        network.sendTo(packet, target);
    }
    public static void sendToAll(PacketBase packet){
        network.sendToAll(packet);
    }
    public static void sendToDimension(PacketBase packet, int dim){
        network.sendToDimension(packet, dim);
    }
    public static void sendToAllAround(PacketBase packet, NetworkRegistry.TargetPoint point){
        network.sendToAllAround(packet, point);
    }
}
