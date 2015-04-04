package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.item.ItemOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class BlockOre extends Block {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);


    public BlockOre() {
        super(Material.rock);

        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.SOULFFORIUM));
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(soundTypePiston);
        setCreativeTab(SpectralProjection.creativeTab);

        GameRegistry.registerBlock(this, ItemOre.class, "ore");
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return blockState.getBaseState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < 4; i++){
            list.add(new ItemStack(item, 1, i));
        }
    }

    public enum EnumType implements IStringSerializable {
        SOULFFORIUM(0, "soulforriumOre"),
        SOULATTITE(1, "soulattiteOre"),
        METRUSITE(2, "metrusiteOre"),
        QUARTZ(3, "quartzOre");

        private final int meta;
        private final String name;
        private final String unlocalizedName;

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
