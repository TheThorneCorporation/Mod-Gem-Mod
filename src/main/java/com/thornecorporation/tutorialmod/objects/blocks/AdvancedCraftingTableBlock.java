package com.thornecorporation.tutorialmod.objects.blocks;

import com.thornecorporation.tutorialmod.objects.containers.AdvancedWorkbenchContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class AdvancedCraftingTableBlock extends Block {
   private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.thornecorporation.advanced_crafting_inventory");

   public AdvancedCraftingTableBlock(Block.Properties properties) {
      super(properties);
   }

   public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult result) {
      if (worldIn.isRemote) {
         return ActionResultType.SUCCESS;
      } else {
         playerIn.openContainer(state.getContainer(worldIn, pos));
         return ActionResultType.SUCCESS;
      }
   }

   public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
      return new SimpleNamedContainerProvider((p_220270_2_, inventory, playerIn) -> {
         return new AdvancedWorkbenchContainer(p_220270_2_, inventory, IWorldPosCallable.of(worldIn, pos));
      }, CONTAINER_NAME);
   }
}
