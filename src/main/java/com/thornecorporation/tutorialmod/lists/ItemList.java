package com.thornecorporation.tutorialmod.lists;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public class ItemList
{
    public static Item mod_gem, mod_gem_block;
    
    //partially implemented Sap
    public static Item sap_bucket;
    
    //basic mod gem tools.
    public static Item mod_gem_sword, mod_gem_axe, mod_gem_pickaxe, mod_gem_shovel, mod_gem_hoe;
    
    //Mod Tool item iter
    public enum ModGemItemTier implements IItemTier {
    	MOD_GEM(4, 2680, 12.0f, 9.0f, 25, () -> {
    	    return Ingredient.fromItems(ItemList.mod_gem);
    	});
    	
    	private final int harvestLevel;
    	private final int maxUses;
    	private final float efficiency;
    	private final float attackDamage;
    	private final int enchantability;
    	private final LazyValue<Ingredient> repairMaterial;

		ModGemItemTier(int harvestLevel, int maxUses, float efficiency,
				float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyValue<>(repairMaterial);
		}

		@Override
		public int getMaxUses() {
			// TODO Auto-generated method stub
			return this.maxUses;
		}

		@Override
		public float getEfficiency() {
			// TODO Auto-generated method stub
			return this.efficiency;
		}

		@Override
		public float getAttackDamage() {
			// TODO Auto-generated method stub
			return this.attackDamage;
		}

		@Override
		public int getHarvestLevel() {
			// TODO Auto-generated method stub
			return this.harvestLevel;
		}

		@Override
		public int getEnchantability() {
			// TODO Auto-generated method stub
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairMaterial() {
			// TODO get ingredient
			return this.repairMaterial.getValue();
		};
    }
}
