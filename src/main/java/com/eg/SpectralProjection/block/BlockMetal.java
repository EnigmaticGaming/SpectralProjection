package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.item.ItemBlockMeta;
import com.eg.SpectralProjection.util.IUnlocalizedNameProvider;
import com.eg.SpectralProjection.util.client.IRenderRegisterHandler;
import com.eg.SpectralProjection.util.client.RenderRegister;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class BlockMetal extends Block implements IUnlocalizedNameProvider, IRenderRegisterHandler {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumVariant.class);

    public BlockMetal() {
        super(Material.rock);

        String name = "blockMetal";
        setUnlocalizedName(name);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumVariant.SOULFFORIUM));
        setHardness(5.0F);
        setResistance(5.0F);
        setStepSound(soundTypePiston);
        setCreativeTab(SpectralProjection.creativeTab);
        setHarvestLevel("pickaxe", 2);

        GameRegistry.registerBlock(this, ItemBlockMeta.class, name);

        //Register ore dict names
        EnumVariant variants[] = EnumVariant.values();
        for(int i = 0; i < variants.length; i++){
            OreDictionary.registerOre("block" + StringUtils.capitalize(variants[i].getUnlocalizedName()), new ItemStack(this, 1, variants[i].getMetadata()));
        }
    }

    @Override
    public BlockState createBlockState() {
        return new BlockState(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return blockState.getBaseState().withProperty(VARIANT, EnumVariant.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumVariant)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        EnumVariant variants[] = EnumVariant.values();
        for(int i = 0; i < variants.length; i++){
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.blockMetal." + EnumVariant.byMetadata(stack.getMetadata()).getUnlocalizedName();
    }

    @Override
    public void registerRenderers() {
        Item item = Item.getItemFromBlock(this);

        EnumVariant[] types = EnumVariant.values();
        for(int i = 0; i < types.length; i++){
            String s = "blockMetal/" + types[i].getUnlocalizedName();

            ModelBakery.addVariantName(item, SpectralProjection.modid + ":" + s);
            RenderRegister.register(this, i, s);
        }
    }

    public enum EnumVariant implements IStringSerializable {
        SOULFFORIUM(0, "soulforrium"),
        SOULATTITE(1, "soulattite");

        private int meta;
        private String name;
        private String unlocalizedName;

        EnumVariant(int meta, String name) {
            this(meta, name, name);
        }

        EnumVariant(int meta, String name, String unlocalizedName) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }


        public static EnumVariant byMetadata(int meta) {
            if (meta < 0 || meta >= values().length) {
                meta = 0;
            }

            return values()[meta];
        }
    }

    public static ItemStack getVariant(EnumVariant variant){
        Block block = SpectralProjection.blockMetal;
        return new ItemStack(block, 1, block.getMetaFromState(block.getDefaultState().withProperty(VARIANT, variant)));
    }
}