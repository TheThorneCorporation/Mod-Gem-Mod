package com.thornecorporation.tutorialmod.objects.containers;

import java.util.Optional;

import com.thornecorporation.tutorialmod.lists.BlockList;
import com.thornecorporation.tutorialmod.objects.recipes.IAdvancedCraftingRecipe;
import com.thornecorporation.tutorialmod.objects.recipes.IThorneRecipeType;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AdvancedWorkbenchContainer extends RecipeBookContainer<AdvancedCraftingInventory> {
   private final AdvancedCraftingInventory craftingGrid = new AdvancedCraftingInventory(this, 3, 3);
   private final AdvancedCraftResultInventory resultSlot = new AdvancedCraftResultInventory();
   private final IWorldPosCallable worldPos;
   private final PlayerEntity player;

   public AdvancedWorkbenchContainer(int id, PlayerInventory playerInv) {
      this(id, playerInv, IWorldPosCallable.DUMMY);
   }

   public AdvancedWorkbenchContainer(int id, PlayerInventory playerInv, IWorldPosCallable p_i50090_3_) {
      super(ContainerType.CRAFTING, id);
      this.worldPos = p_i50090_3_;
      this.player = playerInv.player;
      this.addSlot(new AdvancedCraftingResultSlot(playerInv.player, this.craftingGrid, this.resultSlot, 0, 124, 35));

      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 3; ++j) {
            this.addSlot(new Slot(this.craftingGrid, j + i * 3, 30 + j * 18, 17 + i * 18));
         }
      }

      for(int k = 0; k < 3; ++k) {
         for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
         }
      }

      for(int l = 0; l < 9; ++l) {
         this.addSlot(new Slot(playerInv, l, 8 + l * 18, 142));
      }

   }

   protected static void func_217066_a(int p_217066_0_, World world, PlayerEntity player, AdvancedCraftingInventory craftingInv, AdvancedCraftResultInventory resultInv) {
      if (!world.isRemote) {
         ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
         ItemStack itemstack = ItemStack.EMPTY;
         Optional<IAdvancedCraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(IThorneRecipeType.ADVANCED_CRAFTING, craftingInv, world);
         if (optional.isPresent()) {
            IAdvancedCraftingRecipe iadvancedcraftingrecipe = optional.get();
            if (resultInv.canUseRecipe(world, serverplayerentity, iadvancedcraftingrecipe)) {
               itemstack = iadvancedcraftingrecipe.getCraftingResult(craftingInv);
            }
         }

         resultInv.setInventorySlotContents(0, itemstack);
         serverplayerentity.connection.sendPacket(new SSetSlotPacket(p_217066_0_, 0, itemstack));
      }
   }

   /**
    * Callback for when the crafting matrix is changed.
    */
   public void onCraftMatrixChanged(IInventory inventoryIn) {
      this.worldPos.consume((p_217069_1_, p_217069_2_) -> {
         func_217066_a(this.windowId, p_217069_1_, this.player, this.craftingGrid, this.resultSlot);
      });
   }

   public void func_201771_a(RecipeItemHelper p_201771_1_) {
      this.craftingGrid.fillStackedContents(p_201771_1_);
   }

   public void clear() {
      this.craftingGrid.clear();
      this.resultSlot.clear();
   }

   public boolean matches(IRecipe<? super AdvancedCraftingInventory> recipeIn) {
      return recipeIn.matches(this.craftingGrid, this.player.world);
   }

   /**
    * Called when the container is closed.
    */
   public void onContainerClosed(PlayerEntity playerIn) {
      super.onContainerClosed(playerIn);
      this.worldPos.consume((p_217068_2_, p_217068_3_) -> {
         this.clearContainer(playerIn, p_217068_2_, this.craftingGrid);
      });
   }

   /**
    * Determines whether supplied player can use this container
    */
   public boolean canInteractWith(PlayerEntity playerIn) {
      return isWithinUsableDistance(this.worldPos, playerIn, BlockList.ADVANCED_CRAFTING_TABLE.get());
   }

   /**
    * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
    * inventory and the other inventory(s).
    */
   public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 0) {
            this.worldPos.consume((p_217067_2_, p_217067_3_) -> {
               itemstack1.getItem().onCreated(itemstack1, p_217067_2_, playerIn);
            });
            if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
               return ItemStack.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if (index >= 10 && index < 46) {
            if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
               if (index < 37) {
                  if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                     return ItemStack.EMPTY;
                  }
               } else if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                  return ItemStack.EMPTY;
               }
            }
         } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
            return ItemStack.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.getCount() == itemstack.getCount()) {
            return ItemStack.EMPTY;
         }

         ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
         if (index == 0) {
            playerIn.dropItem(itemstack2, false);
         }
      }

      return itemstack;
   }

   /**
    * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
    * null for the initial slot that was double-clicked.
    */
   public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
      return slotIn.inventory != this.resultSlot && super.canMergeSlot(stack, slotIn);
   }

   public int getOutputSlot() {
      return 0;
   }

   public int getWidth() {
      return this.craftingGrid.getWidth();
   }

   public int getHeight() {
      return this.craftingGrid.getHeight();
   }

   @OnlyIn(Dist.CLIENT)
   public int getSize() {
      return 10;
   }
}