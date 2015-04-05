package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.item.ItemBlockMeta;
import com.eg.SpectralProjection.util.client.IRenderRegisterHandler;
import com.eg.SpectralProjection.util.IUnlocalizedNameProvider;
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
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;
import java.util.Random;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class BlockOre extends Block implements IUnlocalizedNameProvider, IRenderRegisterHandler {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockOre() {
        super(Material.rock);

        setUnlocalizedName("ore");
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.SOULFFORIUM));
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(soundTypePiston);
        setCreativeTab(SpectralProjection.creativeTab);

        GameRegistry.registerBlock(this, ItemBlockMeta.class, "ore");
    }


    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return blockState.getBaseState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        //TODO: do
        return super.getItemDropped(state, rand, fortune);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < 4; i++){
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.ore." + BlockOre.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
    }

    @Override
    public void registerRenderers() {
        Item item = Item.getItemFromBlock(this);

        EnumType[] types = EnumType.values();
        for(int i = 0; i < types.length; i++){
            String s = "ore/" + types[i].getUnlocalizedName();

            ModelBakery.addVariantName(item, SpectralProjection.modid + ":" + s);
            RenderRegister.register(this, i , s);
        }
    }

    public enum EnumType implements IStringSerializable {
        SOULFFORIUM(0, "soulforrium"),
        SOULATTITE(1, "soulattite"),
        METRUSITE(2, "metrusite"),
        QUARTZ(3, "quartz");

        private int meta;
        private String name;
        private String unlocalizedName;

        EnumType(int meta, String name) {
            this(meta, name, name);
        }

        EnumType(int meta, String name, String unlocalizedName) {
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


        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= values().length) {
                meta = 0;
            }

            return values()[meta];
        }
    }
}
