package com.eg.SpectralProjection.util.world.multiblock;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.util.DimensionBlockPos;
import com.eg.SpectralProjection.util.nbt.Tags;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Creysys on 07 Apr 15.
 */
public class MultiblockSpectralContainmentUnit extends Multiblock {

    private ArrayList<Block> allowedBlocks;
    private int maxLength;
    private int maxHeight;
    private int minHeight;
    private Block[][] structure;

    private int height;

    public MultiblockSpectralContainmentUnit(){
        allowedBlocks = new ArrayList<Block>();
        allowedBlocks.add(SpectralProjection.blockSpectralVent);
        allowedBlocks.add(SpectralProjection.blockSpectralStorage);
        allowedBlocks.add(SpectralProjection.blockSpectralDuct);

        maxLength = 5;
        maxHeight = 32;
        minHeight = 5;

        structure = new Block[][]{
                new Block[]{SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent},
                new Block[]{SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralVent},
                new Block[]{SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralDuct, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralVent},
                new Block[]{SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralStorage, SpectralProjection.blockSpectralVent},
                new Block[]{SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent, SpectralProjection.blockSpectralVent}
        };


        height = 0;
    }

    @Override
    public DimensionBlockPos findAnchor(World world, BlockPos pos) {

        int anchorY = pos.getY();
        BlockPos p = new BlockPos(pos.getX(), anchorY - 1, pos.getZ());
        Multiblock multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), p);
        while(allowedBlocks.contains(world.getBlockState(p).getBlock()) && pos.getY() - anchorY < maxHeight && (multiblock == null || multiblock == this)){
            anchorY--;
            p = new BlockPos(pos.getX(), anchorY - 1, pos.getZ());
            multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), p);
        }

        int anchorX = pos.getX();
        p = new BlockPos(anchorX - 1, anchorY, pos.getZ());
        multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), p);
        while(allowedBlocks.contains(world.getBlockState(p).getBlock()) && pos.getX() - anchorX < maxLength && (multiblock == null || multiblock == this)){
            anchorX--;
            p = new BlockPos(anchorX - 1, anchorY, pos.getZ());
            multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), p);
        }

        int anchorZ = pos.getZ();
        p = new BlockPos(anchorX, anchorY, anchorZ - 1);
        multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), p);
        while(allowedBlocks.contains(world.getBlockState(p).getBlock()) && pos.getZ() - anchorZ < maxLength && (multiblock == null || multiblock == this)){
            anchorZ--;
            p = new BlockPos(anchorX, anchorY, anchorZ - 1);
            multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), p);
        }

        return new DimensionBlockPos(world.provider.getDimensionId(), new BlockPos(anchorX, anchorY, anchorZ));
    }

    @Override
    public boolean checkStructure() {
        World world = getWorld();
        DimensionBlockPos anchor = getAnchor();

        for (int yOffset = 0; yOffset < maxHeight; yOffset++) {
            for (int xOffset = 0; xOffset < maxLength; xOffset++) {
                for (int zOffset = 0; zOffset < maxLength; zOffset++) {
                    BlockPos pos = new BlockPos(anchor.pos.getX() + xOffset, anchor.pos.getY() + yOffset, anchor.pos.getZ() + zOffset);
                    Block block = world.getBlockState(pos).getBlock();
                    Multiblock multiblock = Multiblock.getMultiblock(world.provider.getDimensionId(), pos);

                    if(structure[xOffset][zOffset] != block || (multiblock != null && multiblock != this)){
                        height = yOffset + 1;
                        return height >= minHeight + 1;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean contains(BlockPos pos) {
        DimensionBlockPos anchor = getAnchor();
        int x = pos.getX() - anchor.pos.getX();
        int z = pos.getZ() - anchor.pos.getZ();
        int y = pos.getY() - anchor.pos.getY();
        return x >= 0 && x < maxLength && z >= 0 && z < maxLength && y >= 0 && y < height;
    }

    @Override
    public boolean shouldDestroy() {
        World world = getWorld();
        DimensionBlockPos anchor = getAnchor();

        for (int xOffset = 0; xOffset < maxLength; xOffset++) {
            for (int zOffset = 0; zOffset < maxLength; zOffset++) {
                for (int yOffset = 0; yOffset < height; yOffset++) {
                    BlockPos pos = new BlockPos(anchor.pos.getX() + xOffset, anchor.pos.getY() + zOffset, anchor.pos.getZ() + zOffset);
                    Block block = world.getBlockState(pos).getBlock();

                    if(structure[xOffset][zOffset] == block){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void update(World world) {

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger(Tags.Height, height);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        height = compound.getInteger(Tags.Height);
    }
}
