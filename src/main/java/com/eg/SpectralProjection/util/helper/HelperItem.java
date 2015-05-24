package com.eg.SpectralProjection.util.helper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class HelperItem {
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

    public static ItemStack getPrefferedOre(String name) {
        List<ItemStack> oreDictEntries = OreDictionary.getOres(name);
        ItemStack ret = null;

        if (oreDictEntries.size() > 0) {
            ret = oreDictEntries.get(0).copy();

            for (int i = 1; i < oreDictEntries.size(); i++) {
                String modid = GameRegistry.findUniqueIdentifierFor(oreDictEntries.get(i).getItem()).modId;
                if (modid.equals("Minecraft")) {
                    return oreDictEntries.get(i).copy();
                }
            }

        }

        return ret;
    }

    public static ItemStack getStack(Object obj){
        if(obj instanceof ItemStack){
            return ((ItemStack)obj).copy();
        }
        else if(obj instanceof Item){
            return new ItemStack((Item)obj);
        }
        else if(obj instanceof Block){
            return new ItemStack((Block)obj);
        }
        else if(obj instanceof String){
            return getPrefferedOre((String)obj);
        }

        return null;
    }

    public static boolean canMergeStacks(ItemStack stack0, ItemStack stack1){
        return stack0 == null || stack1 == null || (areStacksEqual(stack0, stack1) && stack0.stackSize + stack1.stackSize <= 64);
    }

    public static ItemStack mergeStacks(ItemStack stack0, ItemStack stack1){
        if(!canMergeStacks(stack0, stack1) || (stack0 == null && stack1 == null)){
            return null;
        }

        if(stack0 == null){
            return stack1.copy();
        }
        else if(stack1 == null){
            return stack0.copy();
        }

        return new ItemStack(stack0.getItem(), stack0.stackSize + stack1.stackSize, stack0.getMetadata());
    }
}
