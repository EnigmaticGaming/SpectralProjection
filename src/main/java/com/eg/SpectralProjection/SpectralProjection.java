package com.eg.SpectralProjection;

import com.eg.SpectralProjection.block.BlockOre;
import com.eg.SpectralProjection.item.ItemBase;
import com.eg.SpectralProjection.item.ItemIngot;
import com.eg.SpectralProjection.item.ItemNugget;
import com.eg.SpectralProjection.item.ItemTest;
import com.eg.SpectralProjection.proxy.ProxyServer;
import com.eg.SpectralProjection.world.WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.tools.cmd.Spec;

import java.util.ArrayList;

@Mod(modid = SpectralProjection.MODID, name = SpectralProjection.MODNAME,version = SpectralProjection.VERSION)
public class SpectralProjection
{
    public static final String MODID = "SpectralProjection";
    public static final String modid = "spectralprojection";
    public static final String MODNAME = "Spectral Projection";
    public static final String VERSION = "1.0";

    @Mod.Instance(SpectralProjection.MODID)
    public SpectralProjection instance;

    @SidedProxy(modId = SpectralProjection.MODID, serverSide = "com.eg.SpectralProjection.proxy.ProxyServer", clientSide = "com.eg.SpectralProjection.proxy.ProxyClient")
    public static ProxyServer proxy;

    public static CreativeTabs creativeTab;

    //Blocks
    public static Block blockOre;

    //Items
    public static Item itemTest;
    public static Item itemIngot;
    public static Item itemNugget;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        creativeTab = new SpectralProjectionCreativeTab();

        //Blocks
        blockOre = new BlockOre();

        //Items
        itemTest = new ItemTest();
        itemIngot = new ItemIngot();
        itemNugget = new ItemNugget();


        GameRegistry.registerWorldGenerator(new WorldGenerator(), 2);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Register block renderers
        proxy.registerRenderer(blockOre);

        //Register item renderers
        proxy.registerRenderer(itemTest);
        proxy.registerRenderer(itemIngot);
        proxy.registerRenderer(itemNugget);
    }
}
