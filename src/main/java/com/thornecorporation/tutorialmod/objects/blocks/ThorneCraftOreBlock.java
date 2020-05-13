package com.thornecorporation.tutorialmod.objects.blocks;

import java.util.Random;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

public class ThorneCraftOreBlock extends OreBlock {

	public ThorneCraftOreBlock(Properties properties) {
		super(properties);
	}
	
	protected int getExperience(Random random) {
		return MathHelper.nextInt(random, 10, 15);
	}
	

}
