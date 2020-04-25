package com.thornecorporation.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thornecorporation.tutorialmod.lists.ItemList;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MOD_ID)
public class Main {
	public static Main instance;
    public static final String MOD_ID = "thorneislearningmods";
    public static final ItemGroup THORNECRAFT_TAB = new Main.ThorneCraftItemGroup("thornecraft_group");
    public static final Logger LOGGER = LogManager.getLogger();
    
    public Main()
    {
    	instance = this;
    	
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	
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
			return new ItemStack(ItemList.mod_gem);
		}
        
    }
}

