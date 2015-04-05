package com.eg.SpectralProjection.item;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.util.client.IRenderRegisterHandler;
import com.eg.SpectralProjection.util.client.RenderRegister;
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
public class ItemNugget extends ItemBase implements IRenderRegisterHandler {

    public static ArrayList<String> nuggets;

    public static ItemStack soulforrium;
    public static ItemStack soulattite;
    public static ItemStack metrusite;

    public ItemNugget() {
        super("nugget");

        setMaxDamage(0);
        setHasSubtypes(true);

        registerNuggets();
    }

    public void registerNuggets(){
        nuggets = new ArrayList<String>();

        soulforrium = registerNugget("soulforrium");
        soulattite = registerNugget("soulattite");
        metrusite = registerNugget("metrusite");
    }

    public ItemStack registerNugget(String name){
        nuggets.add(name);
        ItemStack s = new ItemStack(this, 1, nuggets.size() - 1);
        OreDictionary.registerOre("nugget" + StringUtils.capitalize(name), s);
        return s;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, nuggets.size() - 1);
        return "item.nugget." + nuggets.get(i);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < nuggets.size(); i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public void registerRenderers() {
        for(int i = 0; i < nuggets.size(); i++){
            String s = "nugget/" + nuggets.get(i);

            ModelBakery.addVariantName(this, SpectralProjection.modid + ":" + s);
            RenderRegister.register(this, i, s);
        }
    }
}
