package com.eg.SpectralProjection.api.recipe;

import com.eg.SpectralProjection.api.essence.EssenceStack;
import com.eg.SpectralProjection.api.essence.Essences;
import com.eg.SpectralProjection.item.ItemMaterial;

import java.util.Random;

/**
 * Created by Creysys on 28 Apr 15.
 */
public class RecipeSoulforriumFurnaceMetrusite extends RecipeSoulforriumFurnace
{
    private static Random rnd;
    private static int i;

    public static void nextEssence(){
        i = rnd.nextInt(100);
    }



    public RecipeSoulforriumFurnaceMetrusite() {
        super(ItemMaterial.metrusitePaste, null, 200);

        rnd = new Random();
    }

    @Override
    public Object getOutput() {
        nextEssence();

        if(i < 1){
            return new EssenceStack(Essences.greed, 10);
        }
        else if(i < 5){
            return new EssenceStack(Essences.corruption, 10);
        }
        else if(i < 25){
            return new EssenceStack(Essences.unmatter, 10);
        }
        else if(i < 50){
            return new EssenceStack(Essences.pureSoul, 10);
        }
        else{
            return new EssenceStack(Essences.ethreal, 10);
        }
    }
}
