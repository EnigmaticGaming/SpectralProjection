package com.eg.SpectralProjection.world;

import com.eg.SpectralProjection.SpectralProjection;
import com.eg.SpectralProjection.block.BlockOre;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
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
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumVariant.SOULFFORIUM), 0, 16, 4, 2));
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumVariant.SOULATTITE), 2, 29, 6, 8));
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumVariant.METRUSITE), 16, 32, 15, 6));
        ores.add(new WorldGenOre(SpectralProjection.blockOre.getDefaultState().withProperty(BlockOre.VARIANT, BlockOre.EnumVariant.QUARTZ), 16, 32, 4, 15));
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
