package com.eg.SpectralProjection.gui;

import com.eg.SpectralProjection.gui.client.screen.ScreenOuijaBoard;
import com.eg.SpectralProjection.gui.client.screen.ScreenSoulforriumFurnace;
import com.eg.SpectralProjection.gui.client.screen.ScreenSpectralContainmentUnit;
import com.eg.SpectralProjection.gui.container.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.util.Hashtable;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class GuiHandler implements IGuiHandler {

    private static Hashtable<GuiID, Class[]> guis;
    static {
        guis = new Hashtable<GuiID, Class[]>();
        guis.put(GuiID.OUIJABOARD, new Class[]{ContainerOuijaBoard.class, ScreenOuijaBoard.class});
        guis.put(GuiID.SPECTRAL_CONTAINMENT_UNIT, new Class[]{ContainerSpectralContainmentUnit.class, ScreenSpectralContainmentUnit.class});
        guis.put(GuiID.SOULFORRIUM_FURNACE, new Class[]{ContainerSoulforriumFurnace.class, ScreenSoulforriumFurnace.class});
    }


    @SuppressWarnings("unchecked")
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

        if(id < 0 || id >=GuiID.values().length) {
            return null;
        }

        GuiID guiID = GuiID.values()[id];
        if (guis.containsKey(guiID)) {
            try {
                return guis.get(guiID)[0].getConstructor(EntityPlayer.class, World.class, int.class, int.class, int.class).newInstance(player, world, x, y, z);
            } catch (Throwable t) {
                FMLCommonHandler.instance().raiseException(t, "Exception during server gui handling (" + guiID + ")", false);
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        Object container = getServerGuiElement(id, player, world, x, y, z);

        if(container == null){
            return null;
        }

        GuiID guiID = GuiID.values()[id];
        if (guis.containsKey(guiID)) {
            try {
                return guis.get(guiID)[1].getConstructor(ContainerBase.class).newInstance(container);
            } catch (Throwable t) {
                FMLCommonHandler.instance().raiseException(t, "Exception during client gui handling (" + guiID + ")", false);
            }
        }

        return null;
    }
}
