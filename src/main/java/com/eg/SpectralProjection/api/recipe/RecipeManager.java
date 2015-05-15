package com.eg.SpectralProjection.api.recipe;

import java.util.ArrayList;

/**
 * Created by Creysys on 27 Apr 15.
 */
public class RecipeManager {
    public static ArrayList<RecipeSoulforriumFurnace> soulforriumFurnace;

    public static void register(){
        soulforriumFurnace = new ArrayList<RecipeSoulforriumFurnace>();
        soulforriumFurnace.add(new RecipeSoulforriumFurnaceMetrusite());
    }
}
