package com.eg.SpectralProjection.item;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockOre;
import com.eg.SpectralProjection.util.client.IRenderRegisterHandler;
import com.eg.SpectralProjection.util.client.RenderRegister;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class ItemIngot extends ItemBase implements IRenderRegisterHandler {

    public static ArrayList<String> ingots;

    public static ItemStack soulforrium;
    public static ItemStack soulattite;
    public static ItemStack metrusite;

    public ItemIngot() {
        super("ingot");

        setMaxDamage(0);
        setHasSubtypes(true);

        registerIngots();
    }

    public void registerIngots(){
        ingots = new ArrayList<String>();

        soulforrium = registerIngot("soulforrium");
        soulattite = registerIngot("soulattite");
        metrusite = registerIngot("metrusite");
    }

    public ItemStack registerIngot(String name){
        ingots.add(name);
        ItemStack s = new ItemStack(this, 1, ingots.size() - 1);
        OreDictionary.registerOre("ingot" + StringUtils.capitalize(name), s);
        return s;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, ingots.size() - 1);
        return "item.ingot." + ingots.get(i);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < ingots.size(); i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public void registerRenderers() {
        for(int i = 0; i < ingots.size(); i++){
            String s = "ingot/" + ingots.get(i);

            ModelBakery.addVariantName(this, SpectralProjection.modid + ":" + s);
            RenderRegister.register(this, i, s);
        }
    }
}
