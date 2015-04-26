package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.api.essence.Essences;
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

        GuiUtil.drawTooltip(Essences.greed.getLocalizedName(), 8, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip(Essences.corruption.getLocalizedName(), 44, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip(Essences.unmatter.getLocalizedName(), 80, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip(Essences.pureSoul.getLocalizedName(), 116, 63, 16, 16, mouseX, mouseY, this);
        GuiUtil.drawTooltip(Essences.ethreal.getLocalizedName(), 152, 63, 16, 16, mouseX, mouseY, this);
    }

    @Override
    public String getUnlocalizedName() {
        return "spectralContainmentUnit";
    }
}
