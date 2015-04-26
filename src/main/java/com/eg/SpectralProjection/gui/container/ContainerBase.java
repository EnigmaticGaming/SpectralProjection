package com.eg.SpectralProjection.gui.container;

import com.eg.SpectralProjection.util.item.ItemUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Creysys on 06 Apr 15.
 */
public abstract class ContainerBase extends Container {

    public EntityPlayer player;
    public World world;
    public int x;
    public int y;
    public int z;

    public TileEntity tileEntity;

    public ContainerBase(EntityPlayer player, World world, int x, int y, int z) {
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;

        this.tileEntity = world.getTileEntity(new BlockPos(x,y,z));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        if(!(tileEntity instanceof IInventory)){
            return null;
        }


        IInventory tileEntityInventory = (IInventory)tileEntity;

        if(inventorySlots.get(slot) instanceof Slot){
            Slot from = (Slot)inventorySlots.get(slot);
            if(!from.getHasStack())
            {
                return null;
            }

            ItemStack fromStack = from.getStack();

            IInventory to;
            if(from.inventory instanceof InventoryPlayer){
                to = tileEntityInventory;
            }
            else{
                to = player.inventory;
            }

            for(int i = 0; i < to.getSizeInventory(); i++){
                ItemStack toStack = to.getStackInSlot(i);

                if(toStack == null) {
                    if (to.isItemValidForSlot(i, fromStack)) {
                        to.setInventorySlotContents(i, fromStack.copy());
                        from.putStack(null);
                        break;
                    }
                }
                else if(ItemUtil.areStacksEqual(toStack, fromStack) && toStack.stackSize < 64) {

                    int transferred = Math.min(64 - toStack.stackSize, fromStack.stackSize);
                    toStack.stackSize += transferred;

                    if(fromStack.stackSize - transferred <= 0){
                        from.putStack(null);
                        break;
                    }
                    else {
                        fromStack.stackSize -= transferred;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }
}
