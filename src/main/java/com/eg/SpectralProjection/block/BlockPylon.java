package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.item.ItemBlockMeta;
import com.eg.SpectralProjection.util.interfaces.IUnlocalizedNameProvider;
import com.eg.SpectralProjection.util.client.IRenderRegisterHandler;
import com.eg.SpectralProjection.util.client.RenderRegister;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

/**
 * Created by Creysys on 06 Apr 15.
 */
public class BlockPylon extends Block implements IRenderRegisterHandler, IUnlocalizedNameProvider {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumVariant.class);
    public static final PropertyBool ACTIVE = PropertyBool.create("active");


    public BlockPylon() {
        super(Material.rock);

        String name = "pylon";
        setUnlocalizedName(name);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumVariant.SOULFORRIUM).withProperty(ACTIVE, false));
        setHardness(5.0F);
        setResistance(5.0F);
        setStepSound(soundTypeStone);
        setCreativeTab(SpectralProjection.creativeTab);

        GameRegistry.registerBlock(this, ItemBlockMeta.class, name);
    }

    @Override
    public BlockState createBlockState() {
        return new BlockState(this, VARIANT, ACTIVE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        boolean active = (meta & 1) > 0;
        int variant = meta >>> 1;

        return getDefaultState().withProperty(VARIANT, EnumVariant.byMetadata(variant)).withProperty(ACTIVE, active);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int variant = ((EnumVariant) state.getValue(VARIANT)).getMetadata();
        boolean active = (Boolean)state.getValue(ACTIVE);

        int ret = (variant << 1) | (active ? 1 : 0);
        return ret;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        EnumVariant variants[] = EnumVariant.values();
        for(int i = 0; i < variants.length; i++){
            list.add(new ItemStack(this, 1, getMetaFromState(getDefaultState().withProperty(VARIANT, variants[i]))));
        }
    }

    @Override
    public void registerRenderers() {
        Item item = Item.getItemFromBlock(this);

        EnumVariant[] variants = EnumVariant.values();
        for (int i = 0; i < variants.length; i++) {
            String s = "pylon/" + variants[i].getUnlocalizedName();

            if (variants[i].hasState) {
                String states[] = new String[]{"inactive", "active"};
                for (int j = 0; j < states.length; j++) {
                    ModelBakery.addVariantName(item, SpectralProjection.modid + ":" + s + "/" + states[j]);
                }
                s += "/" + states[0];
            } else {
                ModelBakery.addVariantName(item, SpectralProjection.modid + ":" + s);
            }

            RenderRegister.register(this, getMetaFromState(getDefaultState().withProperty(VARIANT, variants[i])), s);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        IBlockState blockState = getStateFromMeta(stack.getMetadata());
        return "tile.pylon." + ((EnumVariant)blockState.getValue(VARIANT)).getUnlocalizedName();
    }


    public enum EnumVariant implements IStringSerializable {
        SOULFORRIUM(0, "soulforrium", false),
        SOULATTITE(1, "soulattite", false),
        SOULURGIST(2, "soulurgist", false),

        CRYSTALLATTICE(3, "crystalLattice", true);

        private int meta;
        private String name;
        private String unlocalizedName;
        public boolean hasState;

        EnumVariant(int meta, String name, boolean hasState) {
            this(meta, name, name, hasState);
        }

        EnumVariant(int meta, String name, String unlocalizedName, boolean hasState) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
            this.hasState = hasState;
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
        Block block = SpectralProjection.blockPylon;
        return new ItemStack(block, 1, block.getMetaFromState(block.getDefaultState().withProperty(VARIANT, variant)));
    }
}
