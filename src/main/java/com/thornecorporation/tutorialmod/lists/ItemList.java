package com.thornecorporation.tutorialmod.lists;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.thornecorporation.tutorialmod.Main;
import com.thornecorporation.tutorialmod.lists.ItemList.ThorneCraftArmorMaterial;

public class ItemList
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<Item> MOD_GEM = ITEMS.register("mod_gem", () -> new Item(new Item.Properties().group(Main.THORNECRAFT_TAB)));
	public static final RegistryObject<Item> MOD_GEM_SWORD = ITEMS.register("mod_gem_sword", () -> new SwordItem(ItemList.ThorneCraftItemTier.MOD_GEM, 3, 1.8f, new Item.Properties().group(Main.THORNECRAFT_TAB)));
	public static final RegistryObject<Item> MOD_GEM_AXE = ITEMS.register("mod_gem_axe", () -> new AxeItem(ItemList.ThorneCraftItemTier.MOD_GEM, 2, 1.2f, new Item.Properties().group(Main.THORNECRAFT_TAB).addToolType(ToolType.AXE, 4)));
	public static final RegistryObject<Item> MOD_GEM_PICKAXE = ITEMS.register("mod_gem_pickaxe", () -> new PickaxeItem(ItemList.ThorneCraftItemTier.MOD_GEM, 1, -2.8f, new Item.Properties().group(Main.THORNECRAFT_TAB).addToolType(ToolType.PICKAXE, 4)));
	public static final RegistryObject<Item> MOD_GEM_SHOVEL = ITEMS.register("mod_gem_shovel", () -> new ShovelItem(ItemList.ThorneCraftItemTier.MOD_GEM, 0, 1.0f, new Item.Properties().group(Main.THORNECRAFT_TAB).addToolType(ToolType.SHOVEL, 4)));
	public static final RegistryObject<Item> MOD_GEM_HOE = ITEMS.register("mod_gem_hoe", () -> new HoeItem(ItemList.ThorneCraftItemTier.MOD_GEM, 3.0f, new Item.Properties().group(Main.THORNECRAFT_TAB)));
	
	public static final RegistryObject<Item> MOD_GEM_HELMET = ITEMS.register("mod_gem_helmet", () -> new ArmorItem(ThorneCraftArmorMaterial.MOD_GEM, EquipmentSlotType.HEAD, new Item.Properties().group(Main.THORNECRAFT_TAB)));
	public static final RegistryObject<Item> MOD_GEM_CHESTPLATE = ITEMS.register("mod_gem_chestplate", () -> new ArmorItem(ThorneCraftArmorMaterial.MOD_GEM, EquipmentSlotType.CHEST, new Item.Properties().group(Main.THORNECRAFT_TAB)));
	public static final RegistryObject<Item> MOD_GEM_LEGGINGS = ITEMS.register("mod_gem_leggings", () -> new ArmorItem(ThorneCraftArmorMaterial.MOD_GEM, EquipmentSlotType.LEGS, new Item.Properties().group(Main.THORNECRAFT_TAB)));
	public static final RegistryObject<Item> MOD_GEM_BOOTS = ITEMS.register("mod_gem_boots", () -> new ArmorItem(ThorneCraftArmorMaterial.MOD_GEM, EquipmentSlotType.FEET, new Item.Properties().group(Main.THORNECRAFT_TAB)));
	
	public static final RegistryObject<Item> SAP_BUCKET = ITEMS.register("sap_bucket", () -> new BucketItem(() ->  FluidList.sap, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	
    
    //Mod Tool item iter
    public enum ThorneCraftItemTier implements IItemTier {
    	MOD_GEM(4, 2680, 12.0f, 7.0f, 25, () -> {
    	    return Ingredient.fromItems(ItemList.MOD_GEM.get());
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
    			4.0F, () -> Ingredient.fromItems(ItemList.MOD_GEM.get()));
    	
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


