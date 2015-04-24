package com.eg.SpectralProjection.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

/**
 * Created by Creysys on 10 Apr 15.
 */
public abstract class ContainerPlayerInventory extends ContainerBase {
    public ContainerPlayerInventory(int playerInventoryX, int playerInventoryY, EntityPlayer player, World world, int x, int y, int z) {
        super(player, world, x, y, z);

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player.inventory, i, playerInventoryX + i * 18, playerInventoryY + 58));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player.inventory, 9 + j + i * 9, playerInventoryX + j * 18, playerInventoryY + i * 18));
            }
        }
    }
}
