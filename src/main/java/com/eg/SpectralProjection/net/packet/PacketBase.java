package com.eg.SpectralProjection.net.packet;

import com.eg.SpectralProjection.net.SPSide;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

/**
 * Created by Creysys on 19 May 15.
 */
public abstract class PacketBase<REQ extends PacketBase> implements IMessage, IMessageHandler<REQ, IMessage> {
    public SPSide side;

    protected PacketBase(SPSide side){
        this.side = side;
    }
}
