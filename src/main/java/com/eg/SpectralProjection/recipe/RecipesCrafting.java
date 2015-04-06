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
        GameRegistry.addRecipe(ItemMaterial.soulurgyCore, "PDP", "DED", "pDp", 'D', Items.diamond, 'E', Items.emerald, 'P', new ItemStack(SpectralProjection.blockPylon, 1, BlockPylon.EnumVariant.SOULFORRIUM.getMetadata()), 'p', new ItemStack(SpectralProjection.blockPylon, 1, BlockPylon.EnumVariant.SOULATTITE.getMetadata()));
        GameRegistry.addRecipe(new ItemStack(SpectralProjection.blockPylon, 1, BlockPylon.EnumVariant.SOULURGIST.getMetadata()), "DMD", "MCM", "DMD", 'D', Items.diamond, 'M', ItemMaterial.metrusitePaste, 'C', ItemMaterial.soulurgyCore);

        //Blocks
        GameRegistry.addRecipe(new ItemStack(SpectralProjection.blockPylon, 1, BlockPylon.EnumVariant.CRYSTALLATTICE.getMetadata()), "CCC", "CPC", "CCC", 'C', Blocks.cobblestone, 'P', ItemMaterial.purifiedCrystal);

        //Metal blocks
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SpectralProjection.blockMetal, 1, BlockMetal.EnumVariant.SOULFFORIUM.getMetadata()), "III", "III", "III", 'I', "ingotSoulforrium"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SpectralProjection.blockMetal, 1, BlockMetal.EnumVariant.SOULATTITE.getMetadata()), "III", "III", "III", 'I', "ingotSoulattite"));

        //Ingots from metal blocks
        GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUtil.setStackCopyAmount(ItemIngot.soulforrium, 9), "blockSoulforrium"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(ItemUtil.setStackCopyAmount(ItemIngot.soulattite, 9), "blockSoulattite"));
    }
}
