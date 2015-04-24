package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.gui.container.ContainerBase;
import com.eg.SpectralProjection.util.client.GuiUtil;

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
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        GuiUtil.drawTooltip("Greed", 8, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip("Corruption", 44, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip("Unmatter", 80, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip("Pure Soul", 116, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip("Ethreal", 152, 63, 16, 16, mouseX, mouseY, this);
    }

    @Override
    public String getUnlocalizedName() {
        return "spectralContainmentUnit";
    }
}
