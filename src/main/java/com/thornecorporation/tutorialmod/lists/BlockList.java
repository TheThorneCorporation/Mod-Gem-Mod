package com.thornecorporation.tutorialmod.lists;

import com.thornecorporation.tutorialmod.Main;
import com.thornecorporation.tutorialmod.objects.blocks.AdvancedCraftingTableBlock;
import com.thornecorporation.tutorialmod.objects.blocks.ThorneCraftOreBlock;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockList
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, Main.MOD_ID);
	
	public static final RegistryObject<Block> MOD_GEM_BLOCK = BLOCKS.register("mod_gem_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(3)));
	public static final RegistryObject<Block> MOD_GEM_ORE = BLOCKS.register("mod_gem_ore", () -> new ThorneCraftOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(4).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(3)));
	public static final RegistryObject<Block> SAP = BLOCKS.register("sap", () -> {return new FlowingFluidBlock(() -> {return FluidList.sap;}, Block.Properties.create(Material.WATER).doesNotBlockMovement().noDrops());});
    
	public static final RegistryObject<Block> ADVANCED_CRAFTING_TABLE = BLOCKS.register("advanced_crafting_table", () -> new AdvancedCraftingTableBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0)));
}
