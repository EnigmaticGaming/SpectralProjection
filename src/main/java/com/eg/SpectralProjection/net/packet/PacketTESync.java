package com.eg.SpectralProjection.net.packet;

import com.eg.SpectralProjection.net.SPSide;
import com.eg.SpectralProjection.util.helper.SerializationHelper;
import com.eg.SpectralProjection.util.interfaces.ITESyncHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Creysys on 19 May 15.
 */
public class PacketTESync extends PacketBase<PacketTESync> {
    public BlockPos pos;
    public byte key;
    public Object value;

    public PacketTESync() {
        super(SPSide.ALL);
    }
    public PacketTESync(BlockPos pos, byte key, Object value){
        this();

        this.pos = pos;
        this.key = key;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = SerializationHelper.readBlockPos(buf);
        key = buf.readByte();
        value = SerializationHelper.readObject(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        SerializationHelper.writeBlockPos(pos, buf);
        buf.writeByte(key);
        SerializationHelper.writeObject(value, buf);
    }

    @Override
    public IMessage onMessage(PacketTESync message, MessageContext ctx) {
        World world;
        if (ctx.side == Side.CLIENT) {
            world = Minecraft.getMinecraft().theWorld;
        } else {
            world = ctx.getServerHandler().playerEntity.worldObj;
        }

        try {
            TileEntity te = world.getTileEntity(message.pos);
            if (te instanceof ITESyncHandler) {
                ((ITESyncHandler) te).handleTESync(message.key, message.value);
            }
        } catch (Exception ex) {
            FMLCommonHandler.instance().raiseException(ex, "Error executing network message", false);
            return null;
        }


        return null;
    }
}
