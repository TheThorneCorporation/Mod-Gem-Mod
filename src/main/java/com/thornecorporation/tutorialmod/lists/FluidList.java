package com.thornecorporation.tutorialmod.lists;

import com.thornecorporation.tutorialmod.events.RegistryEvents;
import com.thornecorporation.tutorialmod.objects.fluids.FluidSap;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;

public class FluidList {
    public static FluidSap.Source sap = null;
    public static FluidSap.Flowing flowing_sap = null;
    
    public static class Tags {
    	public static final Tag<Fluid> SAP = new FluidTags.Wrapper(RegistryEvents.location("sap"));
    }
}
