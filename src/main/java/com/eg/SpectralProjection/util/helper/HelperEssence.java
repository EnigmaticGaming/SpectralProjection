package com.eg.SpectralProjection.util.helper;

import com.eg.SpectralProjection.api.essence.EssenceStack;

/**
 * Created by Creysys on 01 May 15.
 */
public class HelperEssence {
    public static boolean canMergeStacks(EssenceStack stack0, EssenceStack stack1){
        return canMergeStacks(stack0, stack1, -1);
    }
    public static boolean canMergeStacks(EssenceStack stack0, EssenceStack stack1, int maxStackSize){
        return stack0 == null || stack1 == null || (stack0.essence == stack1.essence && (maxStackSize < 0 || stack0.amount + stack1.amount <= maxStackSize));
    }


    public static EssenceStack mergeStacks(EssenceStack stack0, EssenceStack stack1){
        return mergeStacks(stack0, stack1, -1);
    }
    public static EssenceStack mergeStacks(EssenceStack stack0, EssenceStack stack1, int maxStackSize){
        if(!canMergeStacks(stack0, stack1, maxStackSize) || (stack0 == null && stack1 == null)){
            return null;
        }

        if(stack0 == null){
            return stack1.copy();
        }
        else if(stack1 == null){
            return stack0.copy();
        }

        return new EssenceStack(stack0.essence, stack0.amount + stack1.amount);
    }
}
