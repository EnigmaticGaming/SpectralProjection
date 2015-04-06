package com.eg.SpectralProjection.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class ContainerBase extends Container {

    public ContainerBase(EntityPlayer player){
        int playerInventoryX = 8;
        int playerInventoryY = 69;

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player.inventory, i, playerInventoryX + i * 18, playerInventoryY + 58));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player.inventory, 9 + j + i * 9, playerInventoryX + j * 18, playerInventoryY + i * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
