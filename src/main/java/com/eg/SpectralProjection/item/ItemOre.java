package com.eg.SpectralProjection.item;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockOre;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import scala.tools.cmd.Spec;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class ItemOre extends ItemBlock {

    public ItemOre() {
        super(SpectralProjection.blockOre);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return BlockOre.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
    }
}
