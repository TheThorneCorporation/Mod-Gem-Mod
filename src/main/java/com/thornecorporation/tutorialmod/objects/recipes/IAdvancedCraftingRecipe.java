package com.thornecorporation.tutorialmod.objects.recipes;

import com.thornecorporation.tutorialmod.objects.containers.AdvancedCraftingInventory;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;

public interface IAdvancedCraftingRecipe extends IRecipe<AdvancedCraftingInventory> {
	   default IRecipeType<?> getType() {
	      return IThorneRecipeType.ADVANCED_CRAFTING;
	   }
	}