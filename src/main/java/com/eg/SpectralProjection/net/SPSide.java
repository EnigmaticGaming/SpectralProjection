package com.eg.SpectralProjection.net;

import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Creysys on 19 May 15.
 */
public enum SPSide {
    ALL,
    CLIENT,
    SERVER;

    public Side toSide(){
        return (this == SPSide.CLIENT ? Side.CLIENT : Side.SERVER);
    }
}
