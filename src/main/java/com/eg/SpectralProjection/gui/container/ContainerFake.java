package com.eg.SpectralProjection.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Creysys on 10 Apr 15.
 */
public class ContainerFake extends ContainerBase {
    public ContainerFake(EntityPlayer player, World world, int x, int y, int z) {
        super(player, world, x, y, z);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
