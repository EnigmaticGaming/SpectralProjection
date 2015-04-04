package com.eg.SpectralProjection.world;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockOre;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class WorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimensionId()){
            case 0:

                break;
        }
    }

    public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < 16; k++) {
            int firstBlockXCoord = chunkX + rand.nextInt(16);
            int firstBlockZCoord = chunkZ + rand.nextInt(16);
            //Will be found between y = 0 and y = 20
            int quisqueY = rand.nextInt(20);
            BlockPos quisquePos = new BlockPos(firstBlockXCoord, quisqueY, firstBlockZCoord);
            //The 10 as the second parameter sets the maximum vein size
            (new WorldGenMinable(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumType.SOULFFORIUM), 10)).generate(world, rand, quisquePos);
        }
    }
}
