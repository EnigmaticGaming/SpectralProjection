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
public class ItemMaterial extends ItemBase implements IRenderRegisterHandler {

    public static ArrayList<String> materials;

    public static ItemStack metrusitePaste;
    public static ItemStack quartzShard;

    public ItemMaterial() {
        super("material");

        setMaxDamage(0);
        setHasSubtypes(true);

        registerMaterials();
    }

    public void registerMaterials(){
        materials = new ArrayList<String>();

        metrusitePaste = registerMaterial("metrusitePaste");
        quartzShard = registerMaterial("quartzShard");
    }

    public ItemStack registerMaterial(String name){
        materials.add(name);
        ItemStack s = new ItemStack(this, 1, materials.size() - 1);
        return s;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, materials.size() - 1);
        return "item.material." + materials.get(i);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < materials.size(); i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public void registerRenderers() {
        for(int i = 0; i < materials.size(); i++){
            String s = "material/" + materials.get(i);

            ModelBakery.addVariantName(this, SpectralProjection.modid + ":" + s);
            RenderRegister.register(this, i, s);
        }
    }
}
