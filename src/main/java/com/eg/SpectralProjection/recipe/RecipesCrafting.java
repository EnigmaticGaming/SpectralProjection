package com.eg.SpectralProjection.recipe;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockMetal;
import com.eg.SpectralProjection.block.BlockPylon;
import com.eg.SpectralProjection.item.ItemIngot;
import com.eg.SpectralProjection.item.ItemMaterial;
import com.eg.SpectralProjection.item.ItemNugget;
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

        //Materials
        GameRegistry.addRecipe(ItemMaterial.soulurgyCore, "PDP", "DED", "pDp", 'D', Items.diamond, 'E', Items.emerald, 'P', BlockPylon.getVariant(BlockPylon.EnumVariant.SOULFORRIUM), 'p', BlockPylon.getVariant(BlockPylon.EnumVariant.SOULATTITE));
        GameRegistry.addRecipe(ItemMaterial.glyphAnomaly, "_D_", "CEC", "_D_", 'D', Items.diamond, 'E', Items.ender_eye, 'C', ItemMaterial.soulurgyCore);


        //Blocks
        GameRegistry.addRecipe(new ItemStack(SpectralProjection.blockSpectralPump), "DDD", "DCD", "DDD", 'D', Items.diamond, 'C', ItemMaterial.soulurgyCore);
        GameRegistry.addRecipe(new ItemStack(SpectralProjection.blockSpectralDuct), "III", "IGI", "III", 'I', Items.iron_ingot, 'G', Blocks.glass);
        GameRegistry.addRecipe(new ItemStack(SpectralProjection.blockSpectralVent), "IBI", "B_B", "IBI", 'I', Items.iron_ingot, 'B', Blocks.iron_bars);
        GameRegistry.addRecipe(new ItemStack(SpectralProjection.blockSpectralStorage), "RQR", "QCQ", "RQR", 'R', Items.redstone, 'Q', ItemMaterial.quartzShard, 'C', ItemMaterial.soulurgyCore);
        GameRegistry.addRecipe(new ItemStack(SpectralProjection.blockGlyphRune), "CSC", "SGS", "CSC", 'C', Blocks.cobblestone, 'S', Blocks.stone, 'G', ItemMaterial.glyphAnomaly);

        //Pylons
        GameRegistry.addRecipe(BlockPylon.getVariant(BlockPylon.EnumVariant.CRYSTALLATTICE), "CCC", "CPC", "CCC", 'C', Blocks.cobblestone, 'P', ItemMaterial.purifiedCrystal);
        GameRegistry.addRecipe(BlockPylon.getVariant(BlockPylon.EnumVariant.SOULURGIST), "DMD", "MCM", "DMD", 'D', Items.diamond, 'M', ItemMaterial.metrusitePaste, 'C', ItemMaterial.soulurgyCore);

        //Metals
        addMetalRecipes(BlockMetal.getVariant(BlockMetal.EnumVariant.SOULFFORIUM), ItemIngot.soulforrium, ItemNugget.soulforrium);
        addMetalRecipes(BlockMetal.getVariant(BlockMetal.EnumVariant.SOULATTITE), ItemIngot.soulattite, ItemNugget.soulattite);
    }

    private static void addMetalRecipes(ItemStack block, ItemStack ingot, ItemStack nugget){
        GameRegistry.addRecipe(block, "III", "III", "III", 'I', ingot);
        GameRegistry.addRecipe(ingot, "NNN", "NNN", "NNN", 'N', nugget);

        GameRegistry.addRecipe(ItemUtil.setStackCopyAmount(ingot, 9), "B", 'B', block);
        GameRegistry.addRecipe(ItemUtil.setStackCopyAmount(nugget, 9), "I", 'I', ingot);
    }
}
