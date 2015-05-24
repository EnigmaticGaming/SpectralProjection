package com.eg.SpectralProjection.api.recipe;

import com.eg.SpectralProjection.util.helper.HelperItem;
import net.minecraft.item.ItemStack;

/**
 * Created by Creysys on 27 Apr 15.
 */
public class RecipeBase {
    public Object[] input;
    public Object[] output;

    public RecipeBase(Object[] input, Object[] output){
        this.input = input;
        this.output = output;
    }

    public boolean matches(Object[] objs){
        for(int i = 0; i < input.length; i++){
            ItemStack stack = HelperItem.getStack(input[i]);
            if(!contains(stack, objs)){
                return false;
            }
        }

        return true;
    }

    private boolean contains(ItemStack stack, Object[] in){
        int ss = stack.stackSize;
        for(int i = 0; i < in.length; i++){
            ItemStack stack1 = HelperItem.getStack(in[i]);
            if(stack1 == null){
                continue;
            }


            if(HelperItem.areStacksEqual(stack, stack1)){
                ss -= stack1.stackSize;
                if(ss <= 0){
                    return true;
                }
            }
        }

        return false;
    }
}
