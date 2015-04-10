package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.gui.container.ContainerBase;

/**
 * Created by Creysys on 10 Apr 15.
 */
public class ScreenSpectralContainmentUnit extends ScreenBase {
    public ScreenSpectralContainmentUnit(ContainerBase container) {
        super(container);

        xSize = 175;
        ySize = 173;
    }

    @Override
    public String getUnlocalizedName() {
        return "spectralContainmentUnit";
    }
}
