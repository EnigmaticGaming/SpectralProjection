package com.eg.SpectralProjection.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Creysys on 11 Apr 15.
 */
public class ContainerSpectralContainmentUnit extends ContainerPlayerInventory
{
    public ContainerSpectralContainmentUnit(EntityPlayer player, World world, int x, int y, int z) {
        super(8, 92, player, world, x, y, z);
    }
}
