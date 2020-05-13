package com.thornecorporation.tutorialmod.objects.recipes;

import java.util.Optional;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public interface IThorneRecipeType<T extends IRecipe<?>> extends IRecipeType<T> {
   IRecipeType<IAdvancedCraftingRecipe> ADVANCED_CRAFTING = register("advanced_crafting");

   static <T extends IRecipe<?>> IThorneRecipeType<T> register(final String key) {
      return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IThorneRecipeType<T>() {
         public String toString() {
            return key;
         }
      });
   }

   default <C extends IInventory> Optional<T> matches(IRecipe<C> recipe, World worldIn, C inv) {
      return recipe.matches(inv, worldIn) ? Optional.of((T)recipe) : Optional.empty();
   }
}