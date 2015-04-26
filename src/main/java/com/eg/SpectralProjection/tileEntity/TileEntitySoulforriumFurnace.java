package com.eg.SpectralProjection.tileEntity;

import com.eg.SpectralProjection.api.essence.Essence;
import com.eg.SpectralProjection.api.essence.EssenceStack;
import com.eg.SpectralProjection.api.essence.Essences;
import com.eg.SpectralProjection.api.essence.IEssenceHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class TileEntitySoulforriumFurnace extends TileEntity implements IEssenceHandler, IUpdatePlayerListBox, ISidedInventory {

    private ItemStack[] slots;
    private EssenceStack essenceStack;

    public TileEntitySoulforriumFurnace(){
        slots = new ItemStack[getSizeInventory()];
    }

    @Override
    public EssenceStack pullEssence(Essence essence, int max, boolean simulate) {

        if(essenceStack == null || (essence != null && essenceStack.essence != essence)){
            return null;
        }

        int pulled = Math.min(max, essenceStack.amount);

        if(!simulate) {
            essenceStack.amount -= pulled;
            if (essenceStack.amount <= 0) {
                essenceStack = null;
            }
        }

        return new EssenceStack(essence, pulled);
    }

    @Override
    public int pushEssence(EssenceStack stack, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canBind() {
        return false;
    }

    @Override
    public void update() {

    }

    //Inventory
    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN) {
            return new int[]{0};
        }
        return new int[]{1, 2};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction) {
        return direction != EnumFacing.DOWN || TileEntityFurnace.isItemFuel(itemStack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return direction != EnumFacing.DOWN;
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return slots[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (slots[index] != null)
        {
            ItemStack itemstack;

            if (slots[index].stackSize <= count)
            {
                itemstack = slots[index];
                slots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = slots[index].splitStack(count);

                if (slots[index].stackSize == 0)
                {
                    slots[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (slots[index] != null)
        {
            ItemStack itemstack = slots[index];
            slots[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        slots[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {    }

    @Override
    public void closeInventory(EntityPlayer player) {    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 1 || (index == 0 && TileEntityFurnace.isItemFuel(stack));
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < slots.length; i++){
            slots[i] = null;
        }

        essenceStack = null;
    }

    @Override
    public String getName() {
        return getBlockType().getLocalizedName();
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(getName());
    }
}
