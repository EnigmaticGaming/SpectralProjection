package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.gui.container.ContainerBase;
import com.eg.SpectralProjection.tileEntity.TESoulforriumFurnace;
import com.eg.SpectralProjection.util.client.SPTextures;
import com.eg.SpectralProjection.util.helper.HelperRender;

/**
 * Created by Creysys on 23 Apr 15.
 */
public class ScreenSoulforriumFurnace extends ScreenBase
{
    TESoulforriumFurnace soulforriumFurnace;

    public ScreenSoulforriumFurnace(ContainerBase container)
    {
        super(container, SPTextures.screenSoulforriumFurnace);

        soulforriumFurnace = (TESoulforriumFurnace) container.tileEntity;
    }

    @Override
    public String getUnlocalizedName()
    {
        return "soulforriumFurnace";
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mx, int my)
    {
        super.drawGuiContainerBackgroundLayer(partialTicks, mx, my);

        HelperRender.setTexture(SPTextures.essenceProgressEmpty);
        HelperRender.drawTexturedModalRect(guiLeft + 73, guiTop + 59, 0, 0, 13, 10, 13, 10);

        if (soulforriumFurnace.burningTime > 0 && soulforriumFurnace.totalBurningTime > 0)
        {
            HelperRender.setTexture(SPTextures.essenceProgressFull);
            HelperRender.drawTexturedModalRect(guiLeft + 73, guiTop + 59, 0, 0, (int)Math.ceil(13f / (float) soulforriumFurnace.totalBurningTime * (float) soulforriumFurnace.burningTime), 10, 13, 10);
        }

        drawString(fontRendererObj, soulforriumFurnace.craftingTime + "/" + soulforriumFurnace.totalCraftingTime, 250, 80, 10000);
    }
}
