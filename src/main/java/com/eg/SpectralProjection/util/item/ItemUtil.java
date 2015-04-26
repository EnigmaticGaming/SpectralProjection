package com.eg.SpectralProjection.util.item;

import net.minecraft.item.ItemStack;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class ItemUtil {
    public static ItemStack setStackCopyAmount(ItemStack stack, int i){
        ItemStack ret = stack.copy();
        ret.stackSize = i;
        return ret;
    }

    public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2){
        return areStacksEqual(stack1, stack2, false);
    }

    public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2, boolean ignoreNBT) {
        return stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage() && (ignoreNBT || (!stack1.hasTagCompound() && !stack2.hasTagCompound()));
    }

}
