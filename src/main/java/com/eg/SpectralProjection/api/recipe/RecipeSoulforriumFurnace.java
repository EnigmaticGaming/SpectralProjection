package com.eg.SpectralProjection.api.recipe;

/**
 * Created by Creysys on 27 Apr 15.
 */
public class RecipeSoulforriumFurnace extends RecipeBase {

    public int craftingTime;

    public RecipeSoulforriumFurnace(Object input, Object output, int craftingTime) {
        super(new Object[]{input}, new Object[]{output});

        this.craftingTime = craftingTime;
    }

    public boolean matches(Object objs) {
        return super.matches(new Object[]{objs});
    }

    public Object getOutput() {
        return output[0];
    }
}
