package com.thornecorporation.tutorialmod.world.biome;

import com.thornecorporation.tutorialmod.events.RegistryEvents;
import com.thornecorporation.tutorialmod.lists.BlockList;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class ChuckForestBiome extends Biome {

	public ChuckForestBiome() {
		super(new Biome.Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_SAND_CONFIG)
				.precipitation(RainType.RAIN)
				.category(Category.FOREST)
				.downfall(1.0f)
				.depth(0.4f)
				.temperature(0.7f)
				.scale(0.4f)
				.waterColor(0x2c78b2)
				.waterFogColor(0x2c78b2)
				.parent(null)
				);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addExtraEmeraldOre(this);
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addGrass(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addFreezeTopLayer(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		DefaultBiomeFeatures.addStructures(this);
		this.addFeature(Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.MOD_GEM_ORE.get().getDefaultState(), 10))
				.func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(3, 5, 0, 20))));
		
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 8, 4, 4));
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 4, 1, 5));
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 15, 3, 5));
	      this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 6, 1, 7));
	      this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
	      this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
	     this.setRegistryName(RegistryEvents.location("chuck_forest"));
	}
}
