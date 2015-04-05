package com.eg.SpectralProjection.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by Creysys on 05 Apr 15.
 */
public class WorldGenOre {

    public WorldGenMinable worldGenMinable;
    public int yStart;
    public int yEnd;
    public int veinsPerChunk;

    public WorldGenOre(IBlockState blockState,int yStart, int yEnd, int veinSize, int veinsPerChunk) {
        worldGenMinable = new WorldGenMinable(blockState, veinSize);
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.veinsPerChunk = veinsPerChunk;
    }

    public void generate(World world, int chunkX, int chunkZ, Random random){

        int startX = chunkX * 16;
        int startZ = chunkZ * 16;

        for(int i = 0; i < veinsPerChunk; i++) {

            int x = startX + random.nextInt(16);
            int z = startZ + random.nextInt(16);
            int y = yStart + random.nextInt(yEnd - yStart);

            worldGenMinable.generate(world, random, new BlockPos(x, y, z));
        }
    }
}
