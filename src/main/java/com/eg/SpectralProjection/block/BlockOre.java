package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.item.ItemBlockMeta;
import com.eg.SpectralProjection.item.ItemMaterial;
import com.eg.SpectralProjection.util.client.IRenderRegisterHandler;
import com.eg.SpectralProjection.util.IUnlocalizedNameProvider;
import com.eg.SpectralProjection.util.client.RenderRegister;
import com.eg.SpectralProjection.util.item.ItemUtil;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class BlockOre extends Block implements IUnlocalizedNameProvider, IRenderRegisterHandler {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumVariant.class);

    public BlockOre() {
        super(Material.rock);

        setUnlocalizedName("ore");
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumVariant.SOULFFORIUM));
        setHardness(5.0F);
        setResistance(5.0F);
        setStepSound(soundTypePiston);
        setCreativeTab(SpectralProjection.creativeTab);

        GameRegistry.registerBlock(this, ItemBlockMeta.class, "ore");

        //Register ore dict names
        EnumVariant variants[] = EnumVariant.values();
        for(int i = 0; i < variants.length; i++){
            OreDictionary.registerOre("ore" + StringUtils.capitalize(variants[i].getUnlocalizedName()), new ItemStack(this, 1, variants[i].getMetadata()));
        }
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "pickaxe";
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return ((EnumVariant)state.getValue(VARIANT)).getHarvestLevel();
    }

    @Override
    protected BlockState createBlockState() {
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
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

        ItemStack drops = ((EnumVariant)state.getValue(VARIANT)).getDrops();

        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        for(int i = 0; i < drops.stackSize; i++)
        {
            ret.add(ItemUtil.setStackCopyAmount(drops, 1));
        }

        return ret;
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);

        if(blockState == null){
            return 0;
        }

        //make metrusite emit light
        if(blockState.getValue(VARIANT) == EnumVariant.METRUSITE){
            return 6;
        }

        return 0;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < 4; i++){
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.ore." + EnumVariant.byMetadata(stack.getMetadata()).getUnlocalizedName();
    }

    @Override
    public void registerRenderers() {
        Item item = Item.getItemFromBlock(this);

        EnumVariant[] types = EnumVariant.values();
        for(int i = 0; i < types.length; i++){
            String s = "ore/" + types[i].getUnlocalizedName();

            ModelBakery.addVariantName(item, SpectralProjection.modid + ":" + s);
            RenderRegister.register(this, i , s);
        }
    }

    public enum EnumVariant implements IStringSerializable {
        SOULFFORIUM(0, "soulforrium", null, 2),
        SOULATTITE(1, "soulattite", null, 2),
        METRUSITE(2, "metrusite", ItemUtil.setStackCopyAmount(ItemMaterial.metrusitePaste, 3), 1),
        QUARTZ(3, "quartz", ItemUtil.setStackCopyAmount(ItemMaterial.quartzShard, 3), 1);

        private int meta;
        private String name;
        private String unlocalizedName;
        private ItemStack drops;
        private int harvestLevel;

        EnumVariant(int meta, String name, ItemStack drops, int harvestLevel) {
            this(meta, name, name, drops, harvestLevel);
        }

        EnumVariant(int meta, String name, String unlocalizedName, ItemStack drops, int harvestLevel) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;

            this.drops = drops;
            this.harvestLevel = harvestLevel;
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

        public ItemStack getDrops(){
            if(drops == null) {
                return new ItemStack(SpectralProjection.blockOre, 1, meta);
            }
            else{
                return drops.copy();
            }
        }

        public int getHarvestLevel(){
            return harvestLevel;
        }

        public static EnumVariant byMetadata(int meta) {
            if (meta < 0 || meta >= values().length) {
                meta = 0;
            }

            return values()[meta];
        }
    }
}
