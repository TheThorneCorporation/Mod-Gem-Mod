package com.thornecorporation.tutorialmod.events;

import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;

import com.thornecorporation.tutorialmod.Main;
import com.thornecorporation.tutorialmod.lists.BiomeList;
import com.thornecorporation.tutorialmod.lists.BlockList;
import com.thornecorporation.tutorialmod.lists.FluidList;
import com.thornecorporation.tutorialmod.lists.ItemList;
import com.thornecorporation.tutorialmod.lists.ItemList.ThorneCraftArmorMaterial;
import com.thornecorporation.tutorialmod.objects.blocks.ThorneCraftOreBlock;
import com.thornecorporation.tutorialmod.objects.fluids.FluidSap;
import com.thornecorporation.tutorialmod.objects.fluids.FluidSap.Source;
import com.thornecorporation.tutorialmod.world.biome.ChuckForestBiome;
import com.thornecorporation.tutorialmod.objects.fluids.FluidSap.Flowing;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
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
import net.minecraft.world.biome.Biome;
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
	public static void registerFluids(final RegistryEvent.Register<Fluid> event)
	{
		event.getRegistry().registerAll
		(
			FluidList.sap = (Source) new FluidSap.Source().setRegistryName(location("sap")),
			FluidList.flowing_sap = (Flowing) new FluidSap.Flowing().setRegistryName(location("flowing_sap"))
		);
	}
	
	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event)
	{
		event.getRegistry().registerAll
		(
			BiomeList.chuck_forest = new ChuckForestBiome()
		);
		BiomeList.registerBiomes();
	}
	
	
	public static ResourceLocation location(String name)
	{
	    return new ResourceLocation(MOD_ID, name);
	}

}
