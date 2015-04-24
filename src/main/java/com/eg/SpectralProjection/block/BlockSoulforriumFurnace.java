package com.eg.SpectralProjection.block;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.gui.GuiID;
import com.eg.SpectralProjection.tileEntity.TileEntitySoulforriumFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Creysys on 19 Apr 15.
 */
public class BlockSoulforriumFurnace extends BlockContainerBase
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockSoulforriumFurnace() {
        super("soulforriumFurnace", Material.iron);

        GameRegistry.registerTileEntity(TileEntitySoulforriumFurnace.class, "tileEntitySoulforriumFurnace");

        setDefaultState(getDefaultState().withProperty(ACTIVE, false));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySoulforriumFurnace();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        boolean active = meta > 5;

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(ACTIVE, active);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex() + ((Boolean)state.getValue(ACTIVE) ? 5 : 0);
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, ACTIVE);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(world.isRemote){
            return false;
        }

        player.openGui(SpectralProjection.instance, GuiID.SOULFORRIUM_FURNACE.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
