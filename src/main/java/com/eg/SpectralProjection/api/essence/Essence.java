package com.eg.SpectralProjection.api.essence;

import net.minecraft.util.StatCollector;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class Essence {
    public String name;

    public Essence(String name){
        this.name = name;
    }

    public String getLocalizedName(){
        return StatCollector.translateToLocal("essence." + name);
    }
}
