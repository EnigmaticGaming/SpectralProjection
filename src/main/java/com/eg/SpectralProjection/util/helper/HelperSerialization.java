package com.eg.SpectralProjection.util.helper;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by Creysys on 19 May 15.
 */
public class HelperSerialization {

    public static void writeBlockPos(BlockPos pos, ByteBuf buf){
        buf.writeLong(pos.toLong());
    }

    public static BlockPos readBlockPos(ByteBuf buf){
        return BlockPos.fromLong(buf.readLong());
    }


    public static void writeString(String s, ByteBuf buf){
        byte[] buffer = s.getBytes();
        buf.writeByte(buffer.length);
        buf.writeBytes(buffer);
    }

    public static String readString(ByteBuf buf){
        byte length = buf.readByte();
        byte[] buffer = new byte[length];
        buf.readBytes(buffer);
        return new String(buffer);
    }


    public static void writeObject(Object o, ByteBuf buf){
        if(o instanceof Integer){
            buf.writeByte(0);
            buf.writeInt((Integer) o);
        }
        else {
            FMLCommonHandler.instance().raiseException(new Exception(), "Could not serialize object:" + o.getClass(), false);
        }
    }

    public static Object readObject(ByteBuf buf){
        byte b = buf.readByte();
        if(b == 0){
            return buf.readInt();
        }
        else {
            FMLCommonHandler.instance().raiseException(new Exception(), "Could not deserialize object:" + b, false);
            return null;
        }
    }
}
