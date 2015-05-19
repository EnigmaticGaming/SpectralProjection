package com.eg.SpectralProjection.gui.client.screen;

import com.eg.SpectralProjection.gui.container.ContainerBase;
import com.eg.SpectralProjection.tileEntity.TESoulforriumFurnace;

/**
 * Created by Creysys on 23 Apr 15.
 */
public class ScreenSoulforriumFurnace extends ScreenBase {
    TESoulforriumFurnace soulforriumFurnace;

    public ScreenSoulforriumFurnace(ContainerBase container) {
        super(container);

        soulforriumFurnace = (TESoulforriumFurnace)container.tileEntity;
    }

    @Override
    public String getUnlocalizedName() {
        return "soulforriumFurnace";
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        drawString(fontRendererObj, "BT: " + soulforriumFurnace.burningTime + "  TT: " + soulforriumFurnace.totalBurningTime, guiLeft + 100, guiTop + 20, 4210752);
    }
}
