package com.eg.SpectralProjection;

import com.eg.SpectralProjection.achievement.AchievementPageMagic;
import com.eg.SpectralProjection.api.essence.Essences;
import com.eg.SpectralProjection.block.*;
import com.eg.SpectralProjection.entity.EntityShadowMan;
import com.eg.SpectralProjection.event.EventRegister;
import com.eg.SpectralProjection.gui.GuiHandler;
import com.eg.SpectralProjection.item.*;
import com.eg.SpectralProjection.proxy.ProxyServer;
import com.eg.SpectralProjection.recipe.RecipeRegister;
import com.eg.SpectralProjection.util.world.multiblock.MultiblockRegister;
import com.eg.SpectralProjection.util.world.worldData.WorldDataRegister;
import com.eg.SpectralProjection.world.WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

@Mod(modid = SpectralProjection.MODID, name = SpectralProjection.MODNAME,version = SpectralProjection.VERSION)
public class SpectralProjection
{
    public static final String MODID = "SpectralProjection";
    public static final String modid = "spectralprojection";
    public static final String MODNAME = "Spectral Projection";
    public static final String VERSION = "1.0";

    @Mod.Instance(SpectralProjection.MODID)
    public static SpectralProjection instance;

    @SidedProxy(modId = SpectralProjection.MODID, serverSide = "com.eg.SpectralProjection.proxy.ProxyServer", clientSide = "com.eg.SpectralProjection.proxy.ProxyClient")
    public static ProxyServer proxy;

    public static CreativeTabs creativeTab;

    //Items
    ArrayList<Item> items;
    public static Item itemTest;
    public static Item itemSoulTag;

    public static Item itemIngot;
    public static Item itemNugget;
    public static Item itemMaterial;


    //Blocks
    ArrayList<Block> blocks;
    public static Block blockOre;
    public static Block blockMetal;
    public static Block blockOuijaBoard;
    public static Block blockPylon;

    public static Block blockSpectralPump;
    public static Block blockSpectralVent;
    public static Block blockSpectralStorage;
    public static Block blockSpectralDuct;

    public static Block blockGlyphRune;

    public static Block blockSoulforriumFurnace;


    //Achievement Pages
    public static AchievementPage achievementPageMagic;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        creativeTab = new SpectralProjectionCreativeTab();

        //Essences
        Essences.registerEssences();

        //Items
        items = new ArrayList<Item>();

        itemTest = add(new ItemTest());
        itemSoulTag = add(new ItemSoulTag());

        itemIngot = add(new ItemIngot());
        itemNugget = add(new ItemNugget());
        itemMaterial = add(new ItemMaterial());


        //Blocks
        blocks = new ArrayList<Block>();

        blockOre = add(new BlockOre());
        blockMetal = add(new BlockMetal());
        blockOuijaBoard = add(new BlockOuijaBoard());
        blockPylon = add(new BlockPylon());

        blockSpectralPump = add(new BlockSpectralPump());
        blockSpectralVent = add(new BlockSpectralVent());
        blockSpectralStorage = add(new BlockSpectralStorage());
        blockSpectralDuct = add(new BlockSpectralDuct());

        blockGlyphRune = add(new BlockGlyphRune());

        blockSoulforriumFurnace = add(new BlockSoulforriumFurnace());

        //Entities
        add(EntityShadowMan.class, "shadowMan");


        //Achievemnt Pages
        achievementPageMagic = new AchievementPageMagic();

        RecipeRegister.preInitialize();

        GameRegistry.registerWorldGenerator(new WorldGenerator(), 2);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Register everything
        EventRegister.register();
        WorldDataRegister.register();
        MultiblockRegister.register();


        //Register recipes after blocks are initialized
        RecipeRegister.initialize();

        //Register item renderers
        for(int i = 0; i < items.size(); i++){
            proxy.registerRenderer(items.get(i));
        }

        //Register block renderers
        for(int i = 0; i < blocks.size(); i++){
            proxy.registerRenderer(blocks.get(i));
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        RecipeRegister.postInitialize();
    }


    public Item add(Item item){
        items.add(item);
        return item;
    }

    public Block add(Block block){
        blocks.add(block);
        return block;
    }

    public void add(Class<? extends Entity> c, String name){
        EntityRegistry.registerGlobalEntityID(c, name, EntityRegistry.findGlobalUniqueEntityId());
    }
}
