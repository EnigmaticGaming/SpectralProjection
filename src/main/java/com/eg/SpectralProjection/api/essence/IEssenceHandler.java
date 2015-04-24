package com.eg.SpectralProjection.api.essence;

/**
 * Created by Creysys on 19 Apr 15.
 */
public interface IEssenceHandler {
    public EssenceStack pullEssence(Essence essence, int max, boolean simulate);
    public int pushEssence(EssenceStack stack, boolean simulate);
    public boolean canBind();
}
