package com.eg.SpectralProjection.recipe;

import com.eg.SpectralProjection.api.recipe.RecipeManager;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class RecipeRegister {
    public static void preInitialize(){
        //Setup
    }

    public static void initialize(){
        //Default recipes

        RecipesSmelting.register();
        RecipesCrafting.register();

        RecipeManager.register();
    }

    public static void postInitialize(){
        //Mod recipes
    }
}
