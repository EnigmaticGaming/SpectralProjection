package com.eg.SpectralProjection.gui.container;

import com.eg.SpectralProjection.gui.slot.SlotOutput;
import com.eg.SpectralProjection.tileEntity.TileEntitySoulforriumFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Creysys on 23 Apr 15.
 */
public class ContainerSoulforriumFurnace extends ContainerPlayerInventory {
    public ContainerSoulforriumFurnace(EntityPlayer player, World world, int x, int y, int z) {
        super(8, 84, player, world, x, y, z);

        TileEntitySoulforriumFurnace soulforriumFurnace = (TileEntitySoulforriumFurnace)world.getTileEntity(new BlockPos(x,y,z));
        addSlotToContainer(new SlotFurnaceFuel(soulforriumFurnace, 0, 54, 56));
        addSlotToContainer(new Slot(soulforriumFurnace, 1, 90, 56));
        addSlotToContainer(new SlotOutput(soulforriumFurnace, 2, 54, 21));
    }
}
