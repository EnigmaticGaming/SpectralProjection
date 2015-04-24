package com.eg.SpectralProjection.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

/**
 * Created by Creysys on 06 Apr 15.
 */
public abstract class ContainerBase extends Container {

    public EntityPlayer player;
    public World world;
    public int x;
    public int y;
    public int z;

    public ContainerBase(EntityPlayer player, World world, int x, int y, int z) {

        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
