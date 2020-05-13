package com.thornecorporation.tutorialmod.lists;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraft.world.biome.Biome;

public class BiomeList {

	public static Biome chuck_forest;
	
	public static void registerBiomes() {
		registerBiome(chuck_forest, Type.FOREST, Type.RARE);
	}
	
	public static void registerBiome(Biome biome, Type... type)
	{
	    BiomeDictionary.addTypes(biome, type);
	    BiomeManager.addSpawnBiome(biome);
	}

}
