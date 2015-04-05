package com.eg.SpectralProjection.recipe;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockOre;
import com.eg.SpectralProjection.item.ItemIngot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class RecipesSmelting {
    public static void register(){
        GameRegistry.addSmelting(new ItemStack(SpectralProjection.blockOre, 1, BlockOre.EnumVariant.SOULFFORIUM.getMetadata()), ItemIngot.soulforrium, 2);
        GameRegistry.addSmelting(new ItemStack(SpectralProjection.blockOre, 1, BlockOre.EnumVariant.SOULATTITE.getMetadata()), ItemIngot.soulattite, 0.8f);
    }
}
