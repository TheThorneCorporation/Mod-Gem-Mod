package com.thornecorporation.tutorialmod.lists;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.thornecorporation.tutorialmod.Main;

public class ItemList
{
    public static Item mod_gem, mod_gem_block, mod_gem_ore;
    
    //partially implemented Sap
    public static Item sap_bucket;
    
    //basic mod gem tools.
    public static Item mod_gem_sword, mod_gem_axe, mod_gem_pickaxe, mod_gem_shovel, mod_gem_hoe;
    
    //mod gem armor.
    public static Item mod_gem_helmet, mod_gem_chestplate, mod_gem_leggings, mod_gem_boots;
    
    //Mod Tool item iter
    public enum ThorneCraftItemTier implements IItemTier {
    	MOD_GEM(4, 2680, 12.0f, 7.0f, 25, () -> {
    	    return Ingredient.fromItems(ItemList.mod_gem);
    	});
    	
    	private final int harvestLevel;
    	private final int maxUses;
    	private final float efficiency;
    	private final float attackDamage;
    	private final int enchantability;
    	private final LazyValue<Ingredient> repairMaterial;

		ThorneCraftItemTier(int harvestLevel, int maxUses, float efficiency,
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
		}
    }
    
    public enum ThorneCraftArmorMaterial implements IArmorMaterial {
    	MOD_GEM(Main.MOD_ID + ":mod_gem", 45, new int[] {3, 6, 10, 4}, 30, SoundEvents.field_226124_Y_,
    			4.0F, () -> Ingredient.fromItems(ItemList.mod_gem));
    	
    	private static int[] MAX_DAMAGE_ARRAY = new int[] {13, 15, 16, 11};
    	private final String name;
    	private final int maxDamageFactor;
    	private final int[] damageReductionAmountArray;
    	private final int enchantability;
    	private final SoundEvent soundEvent;
    	private final float toughness;
    	private final LazyValue<Ingredient> repairMaterial;
    	
    	private ThorneCraftArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn,
    			SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn){
    		this.name = nameIn;
    		this.maxDamageFactor = maxDamageFactorIn;
    		this.damageReductionAmountArray = damageReductionAmountArrayIn;
    		this.soundEvent = soundEventIn;
    		this.enchantability = enchantabilityIn;
    		this.toughness = toughnessIn;
    		this.repairMaterial = new LazyValue<>(repairMaterialIn);
    	}

		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			// TODO Auto-generated method stub
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * maxDamageFactor;
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			// TODO Auto-generated method stub
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}

		@Override
		public int getEnchantability() {
			// TODO Auto-generated method stub
			return this.enchantability;
		}

		@Override
		public SoundEvent getSoundEvent() {
			// TODO Auto-generated method stub
			return this.soundEvent;
		}

		@Override
		public Ingredient getRepairMaterial() {
			// TODO Auto-generated method stub
			return this.repairMaterial.getValue();
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return this.name;
		}

		@Override
		public float getToughness() {
			// TODO Auto-generated method stub
			return this.toughness;
		}
    	
    	
    	};
    }


