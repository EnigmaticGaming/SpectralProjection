package com.eg.SpectralProjection.recipe;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockMetal;
import com.eg.SpectralProjection.block.BlockPylon;
import com.eg.SpectralProjection.item.ItemIngot;
import com.eg.SpectralProjection.item.ItemMaterial;
import com.eg.SpectralProjection.util.item.ItemUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class RecipesCrafting {
    public static void register(){

        //Items
        GameRegistry.addRecipe(ItemMaterial.soulurgyCore, "PDP", "DED", "pDp", 'D', Items.diamond, 'E', Items.emerald, 'P', BlockPylon.getVariant(BlockPylon.EnumVariant.SOULFORRIUM), 'p', BlockPylon.getVariant(BlockPylon.EnumVariant.SOULATTITE));


        //Blocks
        GameRegistry.addRecipe(BlockPylon.getVariant(BlockPylon.EnumVariant.CRYSTALLATTICE), "CCC", "CPC", "CCC", 'C', Blocks.cobblestone, 'P', ItemMaterial.purifiedCrystal);
        GameRegistry.addRecipe(BlockPylon.getVariant(BlockPylon.EnumVariant.SOULURGIST), "DMD", "MCM", "DMD", 'D', Items.diamond, 'M', ItemMaterial.metrusitePaste, 'C', ItemMaterial.soulurgyCore);

        //Metal blocks
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockMetal.getVariant(BlockMetal.EnumVariant.SOULFFORIUM), "III", "III", "III", 'I', "ingotSoulforrium"));
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockMetal.getVariant(BlockMetal.EnumVariant.SOULATTITE), "III", "III", "III", 'I', "ingotSoulattite"));

        //Ingots from metal blocks
        GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUtil.setStackCopyAmount(ItemIngot.soulforrium, 9), "blockSoulforrium"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUtil.setStackCopyAmount(ItemIngot.soulattite, 9), "blockSoulattite"));
    }
}
