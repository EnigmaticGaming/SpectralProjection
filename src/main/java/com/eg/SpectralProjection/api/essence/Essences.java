package com.eg.SpectralProjection.api.essence;

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
        greed = getEssence("greed");
        corruption = getEssence("corruption");
        unmatter = getEssence("unmatter");
        pureSoul = getEssence("pureSoul");
        ethreal = getEssence("ethreal");
    }

    public static Essence getEssence(String name){
        for(int i = 0; i < registeredEssences.size(); i++){
            Essence essence = registeredEssences.get(i);
            if(essence.name.equals(name)){
                return essence;
            }
        }

        Essence essence = new Essence(name);
        registeredEssences.add(essence);

        System.out.println();

        return essence;
    }

    public static ArrayList<Essence> getRegisteredEssences(){
        return registeredEssences;
    }
}
