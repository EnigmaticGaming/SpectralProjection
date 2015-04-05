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
}
