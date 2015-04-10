package com.eg.SpectralProjection.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class ContainerOuijaBoard extends ContainerPlayerInventory {
    public ContainerOuijaBoard(EntityPlayer player, World world, int x, int y, int z) {
        super(8, 69, player, world, x, y, z);
    }
}
