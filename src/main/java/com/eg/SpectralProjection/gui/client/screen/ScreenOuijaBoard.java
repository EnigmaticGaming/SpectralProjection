package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.gui.container.ContainerBase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class ScreenOuijaBoard extends ScreenBase {

    public ScreenOuijaBoard(ContainerBase container) {
        super(container);
    }

    @Override
    public String getUnlocalizedName() {
        return "ouijaBoard";
    }
}
