package com.eg.SpectralProjection.util.client;

import com.eg.SpectralProjection.SpectralProjection;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Creysys on 24 May 15.
 */
public class SPTextures {

    public static final ResourceLocation screenSpectralContainmentUnit = make("gui/spectralContainmentUnit");
    public static final ResourceLocation screenSoulforriumFurnace = make("gui/soulforriumFurnace");
    public static final ResourceLocation screenOuijaBoard = make("gui/ouijaBoard");



    public static final ResourceLocation essenceGreed = make("gui/elements/essenceIcon/greed");
    public static final ResourceLocation essenceCorruption = make("gui/elements/essenceIcon/corruption");
    public static final ResourceLocation essenceUnmatter = make("gui/elements/essenceIcon/unmatter");
    public static final ResourceLocation essencePureSoul = make("gui/elements/essenceIcon/pureSoul");
    public static final ResourceLocation essenceEthreal = make("gui/elements/essenceIcon/ethreal");


    public static ResourceLocation make(String s){
        return new ResourceLocation(SpectralProjection.modid, "textures/" + s + ".png");
    }
}
