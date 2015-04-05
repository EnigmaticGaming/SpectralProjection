package com.eg.SpectralProjection.item;

import com.eg.SpectralProjection.util.IUnlocalizedNameProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class ItemBlockMeta extends ItemBlock {
    public ItemBlockMeta(Block block) {
        super(block);

        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if(block instanceof IUnlocalizedNameProvider) {
            return ((IUnlocalizedNameProvider) block).getUnlocalizedName(stack);
        }
        return super.getUnlocalizedName(stack);
    }
}
