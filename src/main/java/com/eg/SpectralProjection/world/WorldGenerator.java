package com.eg.SpectralProjection.world;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockOre;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Creysys on 04 Apr 15.
 */
public class WorldGenerator implements IWorldGenerator {

    private ArrayList<WorldGenOre> ores;

    public WorldGenerator(){
        ores = new ArrayList<WorldGenOre>();
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumType.SOULFFORIUM), 0, 50, 10, 5));
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumType.SOULATTITE), 0, 50, 10, 5));
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumType.METRUSITE), 0, 50, 10, 5));
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumType.QUARTZ), 0, 50, 10, 5));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimensionId()){
            case 0:
                generateOverworld(world, random, chunkX, chunkZ);
                break;
        }
    }

    public void generateOverworld(World world, Random random, int chunkX, int chunkZ) {
        for (int i = 0; i < ores.size(); i++) {
            ores.get(i).generate(world, chunkX, chunkZ, random);
        }
    }
}
