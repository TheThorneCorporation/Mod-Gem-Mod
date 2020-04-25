package com.thornecorporation.tutorialmod.events;

import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;

import com.thornecorporation.tutorialmod.Main;
import com.thornecorporation.tutorialmod.lists.BlockList;
import com.thornecorporation.tutorialmod.lists.FluidList;
import com.thornecorporation.tutorialmod.lists.ItemList;
import com.thornecorporation.tutorialmod.objects.fluids.FluidSap;
import com.thornecorporation.tutorialmod.objects.fluids.FluidSap.Source;
import com.thornecorporation.tutorialmod.objects.fluids.FluidSap.Flowing;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents 
{
	public static final Logger LOGGER = Main.LOGGER;
	public static final String MOD_ID = Main.MOD_ID;
	public static final ItemGroup THORNECRAFT = Main.THORNECRAFT_TAB;
			
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
			ItemList.mod_gem = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(location("mod_gem")),
			ItemList.mod_gem_block = new BlockItem(BlockList.mod_gem_block, new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(BlockList.mod_gem_block.getRegistryName()),
			ItemList.sap_bucket = new BucketItem(() ->  FluidList.sap, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(location("sap_bucket")),
			
			//mod gem tools
			ItemList.mod_gem_sword = new SwordItem(ItemList.ModGemItemTier.MOD_GEM, 3, 1.8f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(location("mod_gem_sword")),
			ItemList.mod_gem_pickaxe = new PickaxeItem(ItemList.ModGemItemTier.MOD_GEM, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS).addToolType(ToolType.PICKAXE, 4)).setRegistryName(location("mod_gem_pickaxe")),
			ItemList.mod_gem_axe = new AxeItem(ItemList.ModGemItemTier.MOD_GEM, 2, 1.2f, new Item.Properties().group(ItemGroup.TOOLS).addToolType(ToolType.AXE, 4)).setRegistryName(location("mod_gem_axe")),
			ItemList.mod_gem_shovel = new ShovelItem(ItemList.ModGemItemTier.MOD_GEM, 0, 1.0f, new Item.Properties().group(ItemGroup.TOOLS).addToolType(ToolType.SHOVEL, 4)).setRegistryName(location("mod_gem_shovel")),
			ItemList.mod_gem_hoe = new HoeItem(ItemList.ModGemItemTier.MOD_GEM, 3.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(location("mod_gem_hoe"))
		);
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll
		(
			BlockList.mod_gem_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(3)).setRegistryName(location("mod_gem_block")),
			BlockList.sap = new FlowingFluidBlock(() -> FluidList.sap, Block.Properties.create(Material.WATER).doesNotBlockMovement().noDrops()).setRegistryName(location("sap"))
		);
	}
	
	@SubscribeEvent
	public static void registerFluids(final RegistryEvent.Register<Fluid> event)
	{
		event.getRegistry().registerAll
		(
			FluidList.sap = (Source) new FluidSap.Source().setRegistryName(location("sap")),
			FluidList.flowing_sap = (Flowing) new FluidSap.Flowing().setRegistryName(location("flowing_sap"))
		);
	}
	
	
	public static ResourceLocation location(String name)
	{
	    return new ResourceLocation(MOD_ID, name);
	}

}
