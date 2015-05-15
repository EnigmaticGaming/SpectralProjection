package com.eg.SpectralProjection.util.helper;

import com.eg.SpectralProjection.util.nbt.Tags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Creysys on 15 May 15.
 */
public class NBTUtil {
    public static void writeInventory(ItemStack[] stacks, NBTTagCompound compound){
        NBTTagCompound inner = new NBTTagCompound();

        for(int i = 0; i < stacks.length; i++){
            if(stacks[i] != null){
                NBTTagCompound itemCompound = new NBTTagCompound();
                stacks[i].writeToNBT(itemCompound);
                inner.setTag(String.valueOf(i), itemCompound);
            }
        }

        compound.setTag(Tags.Inventory, inner);
    }

    public static ItemStack[] readInventory(NBTTagCompound compound, int size){
        ItemStack[] stacks = new ItemStack[size];

        NBTTagCompound inner = compound.getCompoundTag(Tags.Inventory);

        for(int i = 0; i < stacks.length; i++){
            if(inner.hasKey(String.valueOf(i))){
                stacks[i] = ItemStack.loadItemStackFromNBT(inner.getCompoundTag(String.valueOf(i)));
            }
        }

        return stacks;
    }
}
