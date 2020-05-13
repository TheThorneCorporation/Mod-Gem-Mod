package com.thornecorporation.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thornecorporation.tutorialmod.lists.BlockList;
import com.thornecorporation.tutorialmod.lists.ItemList;
import com.thornecorporation.tutorialmod.world.gen.ThorneCraftGeneration;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(Main.MOD_ID)
@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class Main {
	public static Main instance;
    public static final String MOD_ID = "thorneislearningmods";
    public static final ItemGroup THORNECRAFT_TAB = new Main.ThorneCraftItemGroup("thornecraft_group");
    public static final Logger LOGGER = LogManager.getLogger();
    
    public Main()
    {
    	instance = this;
    	
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::clientSetup);
    	
    	BlockList.BLOCKS.register(modEventBus);
    	ItemList.ITEMS.register(modEventBus);
    }
    
    @SubscribeEvent
    public static void createBlockItems(final RegistryEvent.Register<Item> event) {
    	final IForgeRegistry<Item> registry = event.getRegistry();
    	
    	BlockList.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
    			final Item.Properties properties = new Item.Properties().group(THORNECRAFT_TAB);
    			final BlockItem blockItem = new BlockItem(block, properties);
    			blockItem.setRegistryName(block.getRegistryName());
    			registry.register(blockItem);
    		});
    }
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	ThorneCraftGeneration.generate();
    }
    
    private void clientSetup(final FMLClientSetupEvent event)
    {
    	
    }
    
    @SuppressWarnings("unused")
	private void onServerStarting(FMLServerStartingEvent event)
    {
    	
    }
    
    public static class ThorneCraftItemGroup extends ItemGroup {

		public ThorneCraftItemGroup(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		@Override
		public ItemStack createIcon() {
			// TODO 
			return new ItemStack(ItemList.MOD_GEM.get());
		}
        
    }
}

