package com.eg.SpectralProjection.recipe;

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
    }

    public static void postInitialize(){
        //Mod recipes
    }
}
