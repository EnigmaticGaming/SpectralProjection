package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.api.essence.Essences;
import com.eg.SpectralProjection.gui.client.screen.element.ElementIcon;
import com.eg.SpectralProjection.gui.container.ContainerBase;
import com.eg.SpectralProjection.util.client.SPTextures;
import com.eg.SpectralProjection.util.helper.HelperGui;
import net.minecraft.util.StatCollector;

/**
 * Created by Creysys on 10 Apr 15.
 */
public class ScreenSpectralContainmentUnit extends ScreenBase {
    public ScreenSpectralContainmentUnit(ContainerBase container) {
        super(container, SPTextures.screenSpectralContainmentUnit);

        xSize = 175;
        ySize = 173;


        add(new ElementIcon(SPTextures.essenceGreed, 8, 63, 16, 16, 16, 16, Essences.greed.getLocalizedName()));
        add(new ElementIcon(SPTextures.essenceCorruption, 44, 63, 16, 16, 16, 16, Essences.corruption.getLocalizedName()));
        add(new ElementIcon(SPTextures.essenceUnmatter, 80, 63, 16, 16, 16, 16, Essences.unmatter.getLocalizedName()));
        add(new ElementIcon(SPTextures.essencePureSoul, 116, 63, 16, 16, 16, 16, Essences.pureSoul.getLocalizedName()));
        add(new ElementIcon(SPTextures.essenceEthreal, 152, 63, 16, 16, 16, 16, Essences.ethreal.getLocalizedName()));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        super.drawGuiContainerForegroundLayer(mx, my);

        //HelperGui.drawTooltip(Essences.greed.getLocalizedName(), 8, 63, 16, 16, mx, my, this);
        //HelperGui.drawTooltip(Essences.corruption.getLocalizedName(), 44, 63, 16, 16, mx, my, this);
        //HelperGui.drawTooltip(Essences.unmatter.getLocalizedName(), 80, 63, 16, 16, mx, my, this);
        //HelperGui.drawTooltip(Essences.pureSoul.getLocalizedName(), 116, 63, 16, 16, mx, my, this);
        //HelperGui.drawTooltip(Essences.ethreal.getLocalizedName(), 152, 63, 16, 16, mx, my, this);
    }

    @Override
    public String getUnlocalizedName() {
        return "spectralContainmentUnit";
    }
}
