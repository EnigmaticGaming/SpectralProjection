package com.eg.SpectralProjection.gui;

import com.eg.SpectralProjection.gui.client.screen.ScreenOuijaBoard;
import com.eg.SpectralProjection.gui.container.ContainerOuijaBoard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch(GuiID.values()[id]){
            case OUIJABOARD:
                return new ContainerOuijaBoard(player);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        Container container = (Container)getServerGuiElement(id, player, world, x, y, z);

        switch(GuiID.values()[id]){
            case OUIJABOARD:
                return new ScreenOuijaBoard(container);
        }

        return null;
    }
}
