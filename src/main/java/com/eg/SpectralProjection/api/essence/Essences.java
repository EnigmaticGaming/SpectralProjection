package com.eg.SpectralProjection.api.essence;

import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class Essences
{
    private static ArrayList<Essence> registeredEssences = new ArrayList<Essence>();

    public static Essence greed;
    public static Essence corruption;
    public static Essence unmatter;
    public static Essence pureSoul;
    public static Essence ethreal;

    public static void registerEssences(){
        greed = getEssence("greed", true);
        corruption = getEssence("corruption", true);
        unmatter = getEssence("unmatter", true);
        pureSoul = getEssence("pureSoul", true);
        ethreal = getEssence("ethreal", true);
    }

    public static Essence getEssence(String name){
        return getEssence(name, false);
    }

    public static Essence getEssence(String name, boolean register){
        for(int i = 0; i < registeredEssences.size(); i++){
            Essence essence = registeredEssences.get(i);
            if(essence.name.equals(name)){
                if(register){
                    FMLCommonHandler.instance().raiseException(new Exception(), "Essence is already registered: " + name, false);
                }
                return essence;
            }
        }

        if(register) {
            Essence essence = new Essence(name);
            registeredEssences.add(essence);

            System.out.println("Essence registred:" + name);

            return essence;
        }

        FMLCommonHandler.instance().raiseException(new Exception(), "Essence not found: " + name, false);
        return null;
    }

    public static ArrayList<Essence> getRegisteredEssences(){
        return registeredEssences;
    }
}
